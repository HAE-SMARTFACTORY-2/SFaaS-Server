package org.hae.sfaas.domain.batteryPack.controller;

import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.batteryPack.service.BatteryPackService;
import org.hae.sfaas.global.common.response.SFaaSResponse;
import org.hae.sfaas.global.common.response.SuccessType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@RequestMapping("/batteryPack")
public class BatteryPackController {

    private final BatteryPackService batteryPackService;

    @GetMapping("/getVoltageInfo")
    public ResponseEntity<SFaaSResponse<?>> getVoltageInfos(@RequestHeader("userId") Long userId,
                                                            @RequestParam(value = "startAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
                                                            @RequestParam(value = "endAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt,
                                                            @RequestParam(value = "filter", required = false, defaultValue = "DATE") String filter){
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_BATTERYPACK_VOLTAGE_SUCCESS, batteryPackService.getVoltageInfo(userId, startAt, endAt, filter)));
    }

    @GetMapping("/getTempInfo")
    public ResponseEntity<SFaaSResponse<?>> getTempInfos(@RequestHeader("userId") Long userId,
                                                         @RequestParam(value = "startAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
                                                         @RequestParam(value = "endAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt,
                                                         @RequestParam(value = "filter", required = false, defaultValue = "DATE") String filter){
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_BATTERYPACK_TEMPERATURE_SUCCESS, batteryPackService.getTempInfo(userId,startAt,endAt,filter)));
    }

    @GetMapping("/getSoCInfo")
    public ResponseEntity<SFaaSResponse<?>> getSocInfos(@RequestHeader("userId") Long userId,
                                                        @RequestParam(value = "startAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
                                                        @RequestParam(value = "endAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt,
                                                        @RequestParam(value = "filter", required = false, defaultValue = "DATE") String filter){
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_BATTERYPACK_SOC_SUCCESS, batteryPackService.getSocInfo(userId,startAt,endAt,filter)));
    }

    @GetMapping("/getDetail")
    public ResponseEntity<SFaaSResponse<?>> getDetailInfos(@RequestHeader("userId") Long userId,
                                                           @RequestParam(value = "startAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
                                                           @RequestParam(value = "endAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt,
                                                           @RequestParam(value = "filter", required = false, defaultValue = "DATE") String filter){
        return  ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_BATTERYPACK_DETAIL_SUCCESS, batteryPackService.getDetailInfo(userId, startAt, endAt, filter)));
    }
}
