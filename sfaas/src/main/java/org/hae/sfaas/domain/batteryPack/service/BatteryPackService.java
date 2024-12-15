package org.hae.sfaas.domain.batteryPack.service;

import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.batteryPack.dto.response.*;
import org.hae.sfaas.domain.batteryPack.mapper.BatteryPackMapper;
import org.hae.sfaas.domain.batteryPack.model.*;
import org.hae.sfaas.domain.user.mapper.UserMapper;
import org.hae.sfaas.domain.user.model.User;
import org.hae.sfaas.global.common.exception.SFaaSException;
import org.hae.sfaas.global.common.response.ErrorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
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

    public List<BatteryFilterGroupResponse> getDetailInfo(Long userId, LocalDate startAt, LocalDate endAt, String filter){
        User user = userMapper.findById(userId);

        if(user == null){
            throw  new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        Long factory_id = user.getFactoryId();

        List<BatteryPackDetail> detailInfos = batteryPackMapper.findDetailInfos(userId, startAt, endAt, filter);

        return detailInfos.stream()
                .collect(Collectors.groupingBy(
                        BatteryPackDetail::getFilterGroup,
                        LinkedHashMap::new,
                        Collectors.mapping(BatteryPackDetailResponse::of,Collectors.toList())
                ))
                .entrySet()
                .stream()
                .map(entry-> new BatteryFilterGroupResponse(entry.getKey(),entry.getValue()))
                .toList();
    }
}
