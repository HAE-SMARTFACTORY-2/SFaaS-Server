package org.hae.sfaas.domain.batteryPack.service;

import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.batteryPack.dto.response.*;
import org.hae.sfaas.domain.batteryPack.mapper.BatteryPackMapper;
import org.hae.sfaas.domain.batteryPack.model.*;
import org.hae.sfaas.domain.user.mapper.UserMapper;
import org.hae.sfaas.domain.user.model.User;
import org.hae.sfaas.domain.welder.model.Status;
import org.hae.sfaas.global.common.exception.SFaaSException;
import org.hae.sfaas.global.common.response.ErrorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BatteryPackService {
    private final UserMapper userMapper;
    private final BatteryPackMapper batteryPackMapper;

    public List<BatteryFilterGroupResponse> getVoltageInfo(Long userId, LocalDate startAt, LocalDate endAt, String filter){
        User user = userMapper.findById(userId);

        if(user == null){
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        Long factory_id = user.getFactoryId();

        List<BatteryPackVoltageInfo> voltageInfos = batteryPackMapper.findVoltageInfos(factory_id,startAt,endAt,filter);

        return voltageInfos.stream()
                .collect(Collectors.groupingBy(
                        BatteryPackVoltageInfo::getFilterGroup,
                        LinkedHashMap::new,
                        Collectors.mapping(BatteryPackVoltageInfoResponse::of,Collectors.toList())
                ))
                .entrySet()
                .stream()
                .map(entry-> new BatteryFilterGroupResponse(entry.getKey(), entry.getValue()))
                .toList();
    }

    public List<BatteryFilterGroupResponse> getTempInfo(Long userId, LocalDate startAt, LocalDate endAt, String filter){
        User user = userMapper.findById(userId);

        if(user == null){
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        Long factory_id = user.getFactoryId();

        List<BatteryPackTempInfo> tempInfos = batteryPackMapper.findTempInfos(factory_id,startAt,endAt,filter);
        return tempInfos.stream()
                .collect(Collectors.groupingBy(
                        BatteryPackTempInfo::getFilterGroup,
                        LinkedHashMap::new,
                        Collectors.mapping(BatteryPackTempInfoResponse::of, Collectors.toList())
                ))
                .entrySet()
                .stream()
                .map(entry-> new BatteryFilterGroupResponse(entry.getKey(),entry.getValue()))
                .toList();
    }

    public List<BatteryFilterGroupResponse> getSocInfo(Long userId, LocalDate startAt, LocalDate endAt, String filter){
        User user = userMapper.findById(userId);

        if(user == null){
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        Long factory_id = user.getFactoryId();

        List<BatteryPackSocInfo> tempInfos = batteryPackMapper.findSocInfos(factory_id,startAt,endAt,filter);
        return tempInfos.stream()
                .collect(Collectors.groupingBy(
                        BatteryPackSocInfo::getFilterGroup,
                        LinkedHashMap::new,
                        Collectors.mapping(BatteryPackSocInfoResponse::of, Collectors.toList())
                ))
                .entrySet()
                .stream()
                .map(entry-> new BatteryFilterGroupResponse(entry.getKey(),entry.getValue()))
                .toList();
    }

    public List<BatteryPackDetailResponse> getDetailInfo(Long userId, LocalDate startAt, LocalDate endAt, Status status){
        User user = userMapper.findById(userId);

        if(user == null){
            throw  new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        Long factory_id = user.getFactoryId();

        List<BatteryPackDetail> detailInfos = batteryPackMapper.findDetailInfos(factory_id, startAt, endAt, status);

        return detailInfos.stream().map(BatteryPackDetailResponse::of).toList();
    }

//    public List<BatteryFilterGroupResponse> getStatusInfo(Long userId, LocalDate startAt, LocalDate endAt,String filter){
//        User user = userMapper.findById(userId);
//
//        if(user == null){
//            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
//        }
//
//        Long factory_id = user.getFactoryId();
//        List<BatteryPackStatus> batteryPackStatus = batteryPackMapper.findStatusInfos(factory_id,startAt,endAt,filter);
//
//        Map<String,int[]> chartDataMap = new LinkedHashMap<>();
//
//        for(BatteryPackStatus packStatus : batteryPackStatus){
//            String filterGroup = packStatus.getFilterGroup();
//            int okCnt = packStatus.getOkCount();
//            int ngCnt = packStatus.getNgCount();
//
//            chartDataMap.computeIfAbsent(filterGroup, k -> new int[3]);
//            chartDataMap.get(filterGroup)[0] += okCnt;
//            chartDataMap.get(filterGroup)[1] += ngCnt;
//            chartDataMap.get(filterGroup)[2] += okCnt + ngCnt;
//        }
//
//        List<BatteryFilterGroupResponse> responses = new ArrayList<>();
//        for (Map.Entry<String, int[]> entry : chartDataMap.entrySet()) {
//            String filterGroup = entry.getKey();
//            int[] statusData = entry.getValue();
//
//            BatteryFilterGroupResponse response = new BatteryFilterGroupResponse(
//                    filterGroup,
//                    Arrays.asList(statusData[0], statusData[1], statusData[2])
//            );
//            responses.add(response);
//        }
//
//        return responses;
//    }
}
