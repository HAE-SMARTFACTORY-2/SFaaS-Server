package org.hae.sfaas.domain.batteryPack.dto.response;

import java.util.List;

public record BatteryFilterGroupResponse<T>(
        String filterGroup,
        List<T> chartData
){
}
