package org.hae.sfaas.domain.welder.service;

import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.user.mapper.UserMapper;
import org.hae.sfaas.domain.user.model.User;
import org.hae.sfaas.domain.welder.dto.response.WeldGateTimeInfoResponse;
import org.hae.sfaas.domain.welder.dto.response.WelderFilterGroupResponse;
import org.hae.sfaas.domain.welder.dto.response.WelderDetailInfoResponse;
import org.hae.sfaas.domain.welder.dto.response.WelderStatusInfoResponse;
import org.hae.sfaas.domain.welder.mapper.WelderMapper;
import org.hae.sfaas.domain.welder.model.DetailWelder;
import org.hae.sfaas.domain.welder.model.Status;
import org.hae.sfaas.domain.welder.model.WelderGateTime;
import org.hae.sfaas.domain.welder.model.WelderStatus;
import org.hae.sfaas.global.common.exception.SFaaSException;
import org.hae.sfaas.global.common.response.ErrorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WelderService {

    private final UserMapper userMapper;
    private final WelderMapper welderMapper;

    public List<WelderFilterGroupResponse> getSpeedInfo(Long userId, LocalDate startAt, LocalDate endAt, String filter) {
        User user = userMapper.findById(userId);
        if (user == null) {
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        Long factoryId = user.getFactoryId();
        List<WelderGateTime> welders = welderMapper.findGateTimeAVGBySpeed(factoryId, startAt, endAt, filter);

        Map<String, List<WeldGateTimeInfoResponse>> chartDataMap = new LinkedHashMap<>();

        for (WelderGateTime welder : welders) {
            String filterGroup = welder.getFilterGroup();
            WeldGateTimeInfoResponse response = WeldGateTimeInfoResponse.of(welder);

            if (response.speed() <= 130) {
                chartDataMap.computeIfAbsent(filterGroup, k -> new ArrayList<>()).add(response);
            } else {
                chartDataMap.computeIfAbsent(filterGroup, k -> new ArrayList<>()).add(response);
            }
        }

        List<WelderFilterGroupResponse> responseList = new ArrayList<>();
        for (Map.Entry<String, List<WeldGateTimeInfoResponse>> entry : chartDataMap.entrySet()) {
            List<WeldGateTimeInfoResponse> A = entry.getValue().stream()
                    .filter(data -> data.speed() <= 130)
                    .collect(Collectors.toList());

            List<WeldGateTimeInfoResponse> B = entry.getValue().stream()
                    .filter(data -> data.speed() > 130)
                    .collect(Collectors.toList());

            Double ALength = A.stream().mapToDouble(WeldGateTimeInfoResponse::length).average().orElse(0.0);
            Double BLength = B.stream().mapToDouble(WeldGateTimeInfoResponse::length).average().orElse(0.0);

            Map<String, Object> chartData = new HashMap<>();
            chartData.put("A", A);
            chartData.put("B", B);
            chartData.put("ALength", ALength);
            chartData.put("BLength", BLength);

            responseList.add(new WelderFilterGroupResponse(entry.getKey(), chartData));
        }

        return responseList;
    }

    public List<WelderDetailInfoResponse> getWeldersInfo(Long userId, LocalDate startAt, LocalDate endAt, Status status) {
        User user = userMapper.findById(userId);
        //TODO - userValidator 재사용 하기
        if(user == null) {
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        // TODO - user가 ADMIN인 경우 전체 factory_id 없이 조회 ? -> 구조 고민 !!

        Long factoryId = user.getFactoryId();
        List<DetailWelder> welders = welderMapper.findAllByfactoryId(factoryId, startAt, endAt, status);

        return welders.stream().map(WelderDetailInfoResponse::of).toList();
    }

    public List<WelderFilterGroupResponse> getStatusInfo(Long userId, LocalDate startAt, LocalDate endAt) {
        User user = userMapper.findById(userId);
        if (user == null) {
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        Long factoryId = user.getFactoryId();
        List<WelderStatus> welders = welderMapper.findStatusCount(factoryId, startAt, endAt);

        Map<String, Map<String, int[]>> chartDataMap = new LinkedHashMap<>();

        for (WelderStatus welder : welders) {
            String filterGroup = welder.getFilterGroup();
            int okCount = welder.getOkCount();
            int ngCount = welder.getNgCount();

            String category = welder.getSpeed() <= 130 ? "A" : "B";

            chartDataMap.computeIfAbsent(filterGroup, k -> new HashMap<>())
                    .computeIfAbsent(category, k -> new int[2]);

            chartDataMap.get(filterGroup).get(category)[0] += okCount;
            chartDataMap.get(filterGroup).get(category)[1] += ngCount;
        }

        List<WelderFilterGroupResponse> responseList = new ArrayList<>();
        for (Map.Entry<String, Map<String, int[]>> entry : chartDataMap.entrySet()) {
            Map<String, Object> chartData = new HashMap<>();

            for (Map.Entry<String, int[]> categoryEntry : entry.getValue().entrySet()) {
                Map<String, Integer> counts = new HashMap<>();
                counts.put("ok", categoryEntry.getValue()[0]);
                counts.put("ng", categoryEntry.getValue()[1]);
                counts.put("total", categoryEntry.getValue()[0] + categoryEntry.getValue()[1]); 
                chartData.put(categoryEntry.getKey(), counts);
            }

            responseList.add(new WelderFilterGroupResponse(entry.getKey(), chartData));
        }

        return responseList;
    }

}
