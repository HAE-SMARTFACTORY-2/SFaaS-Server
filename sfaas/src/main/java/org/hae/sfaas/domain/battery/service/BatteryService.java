package org.hae.sfaas.domain.battery.service;

import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.battery.dto.response.BatteryOutputResponse;
import org.hae.sfaas.domain.battery.dto.response.BatteryStatusResponse;
import org.hae.sfaas.domain.battery.mapper.BatteryMapper;
import org.hae.sfaas.domain.battery.model.BatteryOutput;
import org.hae.sfaas.domain.battery.model.BatteryStatus;

import org.hae.sfaas.domain.batteryPack.dto.response.BatteryFilterGroupResponse;
import org.hae.sfaas.domain.user.mapper.UserMapper;
import org.hae.sfaas.domain.user.model.User;
import org.hae.sfaas.domain.user.model.UserRole;
import org.hae.sfaas.global.common.exception.SFaaSException;
import org.hae.sfaas.global.common.response.ErrorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BatteryService {

    private final UserMapper userMapper;
    private final BatteryMapper batteryMapper;

    public BatteryStatusResponse getBatteryStatusInfo(Long userId, Long factoryId) {
        User user = userMapper.findById(userId);

        if (user == null) {
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        Long fId = null;
        if (user.getUserRole().equals(UserRole.ADMIN) && factoryId != null) {
            fId = factoryId;
        } else {
            fId = user.getFactoryId();
        }

        BatteryStatus batteryStatus = null;
        batteryStatus = batteryMapper.findStatusCountbyfId(userId, fId);

        return BatteryStatusResponse.of(batteryStatus);
    }

    public List<BatteryFilterGroupResponse> getBatteryOutputInfo(Long userId, Long factoryId) {
        User user = userMapper.findById(userId);

        if (user == null) {
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        Long fId = null;
        if (user.getUserRole().equals(UserRole.ADMIN) && factoryId != null) {
            fId = factoryId;
        } else {
            fId = user.getFactoryId();
        }

        List<BatteryOutput> batteryOutput = batteryMapper.findBatteryOutput(userId, fId);

        return batteryOutput.stream()
                .collect(Collectors.groupingBy(
                        BatteryOutput::getFilterGroup,
                        LinkedHashMap::new,
                        Collectors.mapping(BatteryOutputResponse::of, Collectors.toList())
                ))
                .entrySet()
                .stream()
                .map(entry -> new BatteryFilterGroupResponse(entry.getKey(),entry.getValue()))
                .toList();
    }
}
