package org.hae.sfaas.domain.batteryPack.dto.response;

import org.hae.sfaas.domain.batteryPack.model.BatteryPackTempInfo;

public record BatteryPackTempInfoResponse (
            Double cellTempMax,
            Double cellTempMin,
            Double cellTempAvg
){
    public static BatteryPackTempInfoResponse of(BatteryPackTempInfo batteryPackTempInfo){
        return new BatteryPackTempInfoResponse(
                batteryPackTempInfo.getCellTempMax(),
                batteryPackTempInfo.getCellTempMin(),
                batteryPackTempInfo.getCellTempAvg()
        );
    }
}
