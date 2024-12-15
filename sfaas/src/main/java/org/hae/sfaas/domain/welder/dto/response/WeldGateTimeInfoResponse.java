package org.hae.sfaas.domain.welder.dto.response;

import org.hae.sfaas.domain.welder.model.WelderGateTime;

public record WeldGateTimeInfoResponse(
        Double speed,
        Double length,
        Double averageGateOnTime
) {
    public static WeldGateTimeInfoResponse of(WelderGateTime welderGateTime) {
        return new WeldGateTimeInfoResponse(
                welderGateTime.getSpeed(),
                welderGateTime.getAverageLength(),
                welderGateTime.getAverageGateOnTime()
        );
    }
}
