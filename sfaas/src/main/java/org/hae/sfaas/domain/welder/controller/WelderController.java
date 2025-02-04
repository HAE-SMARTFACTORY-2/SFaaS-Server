package org.hae.sfaas.domain.welder.controller;


import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.welder.model.Status;
import org.hae.sfaas.domain.welder.service.WelderService;
import org.hae.sfaas.global.common.response.SFaaSResponse;
import org.hae.sfaas.global.common.response.SuccessType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class WelderController {

    private final WelderService welderService;

    @GetMapping("/welder/speed/gatetime")
    public ResponseEntity<SFaaSResponse<?>> getWelderSpeedInfo(@RequestHeader("userId") Long userId,
                                                               @RequestParam(value = "factoryId", required = false) Long factoryId,
                                                               @RequestParam(value = "startAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
                                                               @RequestParam(value = "endAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt,
                                                               @RequestParam(value = "filter", required = false, defaultValue = "HOUR") String filter) {
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_WELDER_GATETIME_SUCCESS, welderService.getSpeedInfo(userId, factoryId, startAt, endAt, filter)));
    }

    @GetMapping("/welders")
    public ResponseEntity<SFaaSResponse<?>> getWeldersInfo(@RequestHeader("userId") Long userId,
                                                           @RequestParam(value = "factoryId", required = false) Long factoryId,
                                                           @RequestParam(value = "startAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
                                                           @RequestParam(value = "endAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt,
                                                           @RequestParam(value = "pageNum",required = false, defaultValue = "0") int pageNum,
                                                           @RequestParam(value = "status", required = false)Status status) {
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_WELDER_DETAIL_SUCCESS, new HashMap<String, Object>(){{
            put("raw",welderService.getWeldersInfo(userId, factoryId, startAt, endAt, pageNum, status));
            put("pageNum",pageNum);
        }}));
    }

    @GetMapping("/welder/status")
    public ResponseEntity<SFaaSResponse<?>> getWelderStatusInfo(@RequestHeader("userId") Long userId,
                                                                @RequestParam(value = "factoryId", required = false) Long factoryId,
                                                                @RequestParam(value = "startAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
                                                                @RequestParam(value = "endAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt) {
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_WELDER_STATUS_SUCCESS, welderService.getStatusInfo(userId, factoryId, startAt, endAt)));
    }

    @GetMapping("/welder/power")
    public ResponseEntity<SFaaSResponse<?>> getWelderPowerInfo(@RequestHeader("userId") Long userId,
                                                               @RequestParam(value = "factoryId", required = false) Long factoryId,
                                                                @RequestParam(value = "startAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
                                                                @RequestParam(value = "endAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt,
                                                                @RequestParam(value = "filter", required = false, defaultValue = "HOUR") String filter) {
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_WELDER_POWER_SUCCESS, welderService.getPowerInfo(userId, factoryId, startAt, endAt, filter)));
    }

}
