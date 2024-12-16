package org.hae.sfaas.domain.batteryPack.controller;

import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.batteryPack.service.BatteryPackService;
import org.hae.sfaas.domain.welder.model.Status;
import org.hae.sfaas.global.common.response.SFaaSResponse;
import org.hae.sfaas.global.common.response.SuccessType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class BatteryPackController {

    private final BatteryPackService batteryPackService;

    @GetMapping("/batterypack/voltage")
    public ResponseEntity<SFaaSResponse<?>> getVoltageInfos(@RequestHeader("userId") Long userId,
                                                            @RequestParam(value = "startAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
                                                            @RequestParam(value = "endAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt,
                                                            @RequestParam(value = "filter", required = false, defaultValue = "DATE") String filter){
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_BATTERYPACK_VOLTAGE_SUCCESS, batteryPackService.getVoltageInfo(userId, startAt, endAt, filter)));
    }

    @GetMapping("/batterypack/temperature")
    public ResponseEntity<SFaaSResponse<?>> getTempInfos(@RequestHeader("userId") Long userId,
                                                         @RequestParam(value = "startAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
                                                         @RequestParam(value = "endAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt,
                                                         @RequestParam(value = "filter", required = false, defaultValue = "DATE") String filter){
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_BATTERYPACK_TEMPERATURE_SUCCESS, batteryPackService.getTempInfo(userId,startAt,endAt,filter)));
    }

    @GetMapping("/batterypack/socvoltage")
    public ResponseEntity<SFaaSResponse<?>> getSocInfos(@RequestHeader("userId") Long userId,
                                                        @RequestParam(value = "startAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
                                                        @RequestParam(value = "endAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt,
                                                        @RequestParam(value = "filter", required = false, defaultValue = "DATE") String filter){
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_BATTERYPACK_SOC_SUCCESS, batteryPackService.getSocInfo(userId,startAt,endAt,filter)));
    }

    @GetMapping("/batterypacks")
    public ResponseEntity<SFaaSResponse<?>> getDetailInfos(@RequestHeader("userId") Long userId,
                                                           @RequestParam(value = "startAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
                                                           @RequestParam(value = "endAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt,
                                                           @RequestParam(value = "status", required = false) Status status){
        return  ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_BATTERYPACK_DETAIL_SUCCESS, batteryPackService.getDetailInfo(userId, startAt, endAt,status)));
    }

    @GetMapping("/batterypack/status")
    public ResponseEntity<SFaaSResponse<?>> getStatus(@RequestHeader("userId") Long userId,
                                                      @RequestParam(value = "startAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
                                                      @RequestParam(value = "endAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt,
                                                      @RequestParam(value = "filter", required = false, defaultValue = "DATE") String filter){

        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_BATTERYPACK_STATUS_SUCCESS,batteryPackService.getStatusInfo(userId, startAt, endAt,filter)));
    }
}
