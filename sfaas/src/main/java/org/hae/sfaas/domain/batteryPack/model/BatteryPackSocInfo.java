package org.hae.sfaas.domain.batteryPack.model;

import lombok.Data;

@Data
public class BatteryPackSocInfo {
    private Double rsocMin;
    private Double rsocMax;
    private Double rsocAvg;
    private Double usocMin;
    private Double usocMax;
    private Double usocAvg;
    private String filterGroup;
}
