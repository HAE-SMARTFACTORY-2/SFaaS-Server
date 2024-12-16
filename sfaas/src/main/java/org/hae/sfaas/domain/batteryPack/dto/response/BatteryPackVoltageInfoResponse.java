package org.hae.sfaas.domain.batteryPack.dto.response;

import org.hae.sfaas.domain.batteryPack.model.BatteryPackVoltageInfo;

public record BatteryPackVoltageInfoResponse (
        Double cellVoltageMin,
        Double cellVoltageMax,
        Double cellVoltageDv
){
    public static BatteryPackVoltageInfoResponse of(BatteryPackVoltageInfo batteryPackVoltageInfo){
        return new BatteryPackVoltageInfoResponse(
                batteryPackVoltageInfo.getCellVoltageMin(),
                batteryPackVoltageInfo.getCellVoltageMax(),
                batteryPackVoltageInfo.getCellVoltageDv()
        );
    }
}
