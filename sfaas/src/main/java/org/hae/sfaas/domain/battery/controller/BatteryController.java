package org.hae.sfaas.domain.battery.controller;

import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.battery.service.BatteryService;
import org.hae.sfaas.global.common.response.SFaaSResponse;
import org.hae.sfaas.global.common.response.SuccessType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BatteryController {

    private final BatteryService batteryService;

    @GetMapping("/battery/status")
    public ResponseEntity<SFaaSResponse<?>> getBatteryStatusInfo(@RequestHeader("userId") Long userId,
                                                                @RequestParam(value = "factoryId", required = false) Long factoryId){
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_BATTERY_STATUS_SUCCESS, batteryService.getBatteryStatusInfo(userId, factoryId)));
    }

    @GetMapping("/battery/output")
    public ResponseEntity<SFaaSResponse<?>> getBatteryOutputInfo(@RequestHeader("userId") Long userId,
                                                                @RequestParam(value = "factoryId", required = false) Long factoryId){
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_BATTERY_OUTPUT_SUCCESS, batteryService.getBatteryOutputInfo(userId, factoryId)));
    }

}
