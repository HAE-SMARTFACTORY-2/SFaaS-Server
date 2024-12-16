package org.hae.sfaas.domain.battery.dto.response;

import org.hae.sfaas.domain.battery.model.BatteryOutput;

public record BatteryOutputResponse(
        Long factoryId,
        int totalCount
) {
    public static BatteryOutputResponse of(BatteryOutput batteryOutput){
        return new BatteryOutputResponse(
                batteryOutput.getFactoryId(),
                batteryOutput.getTotalCount()
        );
    }
}
