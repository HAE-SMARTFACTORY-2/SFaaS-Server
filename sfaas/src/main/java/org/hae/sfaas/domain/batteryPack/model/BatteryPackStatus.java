package org.hae.sfaas.domain.batteryPack.model;

import lombok.Data;
import org.hae.sfaas.domain.welder.model.Status;

@Data
public class BatteryPackStatus {
    private int okCount;
    private int ngCount;
    private int totalCount;
    private Double cellVoltageDv;
    private String filterGroup;
}
