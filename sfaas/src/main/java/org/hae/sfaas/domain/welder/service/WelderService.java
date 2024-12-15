package org.hae.sfaas.domain.welder.service;

import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.user.mapper.UserMapper;
import org.hae.sfaas.domain.user.model.User;
import org.hae.sfaas.domain.welder.dto.response.WeldGateTimeInfoResponse;
import org.hae.sfaas.domain.welder.dto.response.WelderFilterGroupResponse;
import org.hae.sfaas.domain.welder.dto.response.WelderDetailInfoResponse;
import org.hae.sfaas.domain.welder.mapper.WelderMapper;
import org.hae.sfaas.domain.welder.model.DetailWelder;
import org.hae.sfaas.domain.welder.model.Status;
import org.hae.sfaas.domain.welder.model.WelderGateTime;
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

}
