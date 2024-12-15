package org.hae.sfaas.domain.welder.dto.response;

import org.hae.sfaas.domain.welder.model.DetailWelder;
import org.hae.sfaas.domain.welder.model.Status;

import java.time.LocalDateTime;

public record WelderDetailInfoResponse(
        Long welderId,
        String factoryCode,
        String factoryLocation,
        Long speed,
        Long length,
        Long realPower,
        Long setDuty,
        Long setPower,
        Long gateOnTime,
        LocalDateTime createAt,
        Status status
) {
    public static WelderDetailInfoResponse of(DetailWelder detailWelder) {
        return new WelderDetailInfoResponse(
                detailWelder.getWelderId(),
                detailWelder.getFactoryCode(),
                detailWelder.getFactoryLocation(),
                detailWelder.getSpeed(),
                detailWelder.getLength(),
                detailWelder.getRealPower(),
                detailWelder.getSetDuty(),
                detailWelder.getSetPower(),
                detailWelder.getGateOnTime(),
                detailWelder.getCreateAt(),
                detailWelder.getStatus()
        );
    }
}