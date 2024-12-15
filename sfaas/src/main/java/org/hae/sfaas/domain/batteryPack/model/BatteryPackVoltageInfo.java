package org.hae.sfaas.domain.batteryPack.model;

import lombok.Data;
import org.hae.sfaas.domain.welder.model.Status;

import java.time.LocalDateTime;

@Data
public class BatteryPackVoltageInfo {
    private Double cellVoltageMin;
    private Double cellVoltageMax;
    private Double cellVoltageDv;
    private String filterGroup;
}
