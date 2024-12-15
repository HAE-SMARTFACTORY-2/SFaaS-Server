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

@RestController
@RequiredArgsConstructor
public class WelderController {

    private final WelderService welderService;

    @GetMapping("/welder/speed/gatetime")
    public ResponseEntity<SFaaSResponse<?>> getWelderSpeedInfo(@RequestHeader("userId") Long userId,
                                                               @RequestParam(value = "startAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
                                                               @RequestParam(value = "endAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt,
                                                               @RequestParam(value = "filter", required = false) String filter) {
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_WELDER_GATETIME_SUCCESS, welderService.getSpeedInfo(userId, startAt, endAt, filter)));
    }

    @GetMapping("/welders")
    public ResponseEntity<SFaaSResponse<?>> getWeldersInfo(@RequestHeader("userId") Long userId,
                                                           @RequestParam(value = "startAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
                                                           @RequestParam(value = "endAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt,
                                                           @RequestParam(value = "status", required = false)Status status) {
        //TODO - SUCCESSTYPE 변경
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.OK, welderService.getWeldersInfo(userId, startAt, endAt, status)));
    }

}
