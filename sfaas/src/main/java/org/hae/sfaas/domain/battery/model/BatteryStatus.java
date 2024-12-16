package org.hae.sfaas.domain.battery.model;

import lombok.Data;

@Data
public class BatteryStatus {
    private Long factoryId;
    private int okCount;
    private int ngCount;
}
