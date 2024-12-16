package org.hae.sfaas.domain.battery.service;

import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.battery.dto.response.BatteryStatusResponse;
import org.hae.sfaas.domain.battery.mapper.BatteryMapper;
import org.hae.sfaas.domain.battery.model.BatteryStatus;

import org.hae.sfaas.domain.user.mapper.UserMapper;
import org.hae.sfaas.domain.user.model.User;
import org.hae.sfaas.domain.user.model.UserRole;
import org.hae.sfaas.global.common.exception.SFaaSException;
import org.hae.sfaas.global.common.response.ErrorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        BatteryStatus batteryStatus = null;
        if (user.getUserRole().equals(UserRole.ADMIN) && factoryId != null) {
            batteryStatus = batteryMapper.findStatusCountbyfId(userId, factoryId);
        } else {
            batteryStatus = batteryMapper.findStatusCountbyfId(userId, user.getFactoryId());
        }

        return BatteryStatusResponse.of(batteryStatus);
    }

}
