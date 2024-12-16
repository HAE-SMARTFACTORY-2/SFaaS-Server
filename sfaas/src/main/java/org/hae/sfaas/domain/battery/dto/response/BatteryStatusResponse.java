package org.hae.sfaas.domain.battery.dto.response;

import org.hae.sfaas.domain.battery.model.BatteryStatus;

public record BatteryStatusResponse<T>(
        Long factoryId,
        int okCount,
        int ngCount
){
    public static BatteryStatusResponse of(BatteryStatus batteryStatus){
        return new BatteryStatusResponse(
                batteryStatus.getFactoryId(),
                batteryStatus.getOkCount(),
                batteryStatus.getNgCount()
        );
    }
}
