package org.hae.sfaas.domain.batteryPack.dto.response;

import org.hae.sfaas.domain.batteryPack.model.BatteryPackStatus;

public record BatteryPackStatusResponse(
    int okCount,
    int ngCount,
    int totalCount,
    Double cellVoltageDv
){
    public static BatteryPackStatusResponse of(BatteryPackStatus batteryPackStatus){
        return new BatteryPackStatusResponse(
                batteryPackStatus.getOkCount(),
                batteryPackStatus.getNgCount(),
                batteryPackStatus.getOkCount() + batteryPackStatus.getNgCount(),
                (batteryPackStatus.getCellVoltageDv())
        );
    }
}
