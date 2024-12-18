package org.hae.sfaas.domain.aging.controller;

import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.aging.service.AgingService;
import org.hae.sfaas.global.common.response.SFaaSResponse;
import org.hae.sfaas.global.common.response.SuccessType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class AgingController {
    private final AgingService agingService;

    //외부온도
    @GetMapping("/aging/temperature")
    public ResponseEntity<SFaaSResponse<?>> getTempInfos(@RequestHeader("userId") Long userId,
                                                         @RequestParam(value = "startAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
                                                         @RequestParam(value = "endAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt,
                                                         @RequestParam(value = "factoryId", required = false) Long factoryId,
                                                         @RequestParam(value = "filter", required = false, defaultValue = "HOUR") String filter){
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_AGING_TEMPERATURE_SUCCESS,agingService.getTempInfo(userId,factoryId,startAt,endAt,filter)));
    }
    //양품불량품
    @GetMapping("/aging/status")
    public ResponseEntity<SFaaSResponse<?>> getStatusInfos(@RequestHeader("userId") Long userId,
                                                           @RequestParam(value = "startAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startAt,
                                                           @RequestParam(value = "endAt", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endAt,
                                                           @RequestParam(value = "factoryId", required = false) Long factoryId,
                                                           @RequestParam(value = "filter", required = false, defaultValue = "HOUR") String filter){
        return ResponseEntity.status(HttpStatus.OK).body(SFaaSResponse.success(SuccessType.GET_AGING_STATUS_SUCCESS,agingService.getStatusInfo(userId, factoryId, startAt, endAt, filter)));
    }
}
