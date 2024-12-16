package org.hae.sfaas.domain.batteryPack.dto.response;

import org.hae.sfaas.domain.batteryPack.model.BatteryPack;
import org.hae.sfaas.domain.batteryPack.model.BatteryPackSocInfo;

public record BatteryPackSocInfoResponse(
        Double rsocMin,
        Double rsocMax,
        Double rsocAvg,
        Double usocMin,
        Double usocMax,
        Double usocAvg
){
    public static BatteryPackSocInfoResponse of(BatteryPackSocInfo batteryPackSocInfo){
        return new BatteryPackSocInfoResponse(
                batteryPackSocInfo.getRsocMin(),
                batteryPackSocInfo.getRsocMax(),
                batteryPackSocInfo.getRsocAvg(),
                batteryPackSocInfo.getUsocMin(),
                batteryPackSocInfo.getUsocMax(),
                batteryPackSocInfo.getUsocAvg()
        );
    }
}
