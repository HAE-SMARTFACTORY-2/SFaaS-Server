package org.hae.sfaas.domain.batteryPack.service;

import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.batteryPack.dto.response.*;
import org.hae.sfaas.domain.batteryPack.mapper.BatteryPackMapper;
import org.hae.sfaas.domain.batteryPack.model.*;
import org.hae.sfaas.domain.user.mapper.UserMapper;
import org.hae.sfaas.domain.user.model.User;
import org.hae.sfaas.domain.user.model.UserRole;
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

    public List<BatteryFilterGroupResponse> getVoltageInfo(Long userId, Long factoryId, LocalDate startAt, LocalDate endAt, String filter){
        User user = userMapper.findById(userId);

        if(user == null){
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        Long fId = null;
        if (user.getUserRole().equals(UserRole.ADMIN) && factoryId != null) {
            fId = factoryId;
        } else {
            fId = user.getFactoryId();
        }

        List<BatteryPackVoltageInfo> voltageInfos = batteryPackMapper.findVoltageInfos(fId,startAt,endAt,filter);

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

    public List<BatteryFilterGroupResponse> getTempInfo(Long userId, Long factoryId, LocalDate startAt, LocalDate endAt, String filter){
        User user = userMapper.findById(userId);

        if(user == null){
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        Long fId = null;
        if (user.getUserRole().equals(UserRole.ADMIN) && factoryId != null) {
            fId = factoryId;
        } else {
            fId = user.getFactoryId();
        }

        List<BatteryPackTempInfo> tempInfos = batteryPackMapper.findTempInfos(fId,startAt,endAt,filter);
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

    public List<BatteryFilterGroupResponse> getSocInfo(Long userId, Long factoryId, LocalDate startAt, LocalDate endAt, String filter){
        User user = userMapper.findById(userId);

        if(user == null){
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        Long fId = null;
        if (user.getUserRole().equals(UserRole.ADMIN) && factoryId != null) {
            fId = factoryId;
        } else {
            fId = user.getFactoryId();
        }

        List<BatteryPackSocInfo> tempInfos = batteryPackMapper.findSocInfos(fId,startAt,endAt,filter);
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

    public List<BatteryPackDetailResponse> getDetailInfo(Long userId, Long factoryId, LocalDate startAt, LocalDate endAt,int pageNum, Status status){
        User user = userMapper.findById(userId);

        if(user == null){
            throw  new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        Long fId = null;
        if (user.getUserRole().equals(UserRole.ADMIN) && factoryId != null) {
            fId = factoryId;
        } else {
            fId = user.getFactoryId();
        }

        Long offset = (long) (pageNum * 100);

        List<BatteryPackDetail> detailInfos = batteryPackMapper.findDetailInfos(fId, startAt, endAt, offset, status);

        return detailInfos.stream().map(BatteryPackDetailResponse::of).toList();
    }

    public List<BatteryFilterGroupResponse> getStatusInfo(Long userId, Long factoryId, LocalDate startAt, LocalDate endAt,String filter){
        User user = userMapper.findById(userId);

        if(user == null){
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        Long fId = null;
        if (user.getUserRole().equals(UserRole.ADMIN) && factoryId != null) {
            fId = factoryId;
        } else {
            fId = user.getFactoryId();
        }

        List<BatteryPackStatus> batteryPackStatus = batteryPackMapper.findStatusCount(fId, startAt, endAt, filter);

        return batteryPackStatus.stream()
                .collect(Collectors.groupingBy(
                        BatteryPackStatus::getFilterGroup,
                        LinkedHashMap::new,
                        Collectors.mapping(BatteryPackStatusResponse::of, Collectors.toList())
                ))
                .entrySet()
                .stream()
                .map(entry -> new BatteryFilterGroupResponse(entry.getKey(),entry.getValue()))
                .toList();
    }
}
