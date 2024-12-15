package org.hae.sfaas.domain.batteryPack.model;

import lombok.Data;

@Data
public class BatteryPackTempInfo {
    private Double cellTempMax;
    private Double cellTempMin;
    private Double cellTempAvg;
    private String filterGroup;
}
