package org.hae.sfaas.domain.batteryPack.model;

import lombok.Data;
import org.hae.sfaas.domain.welder.model.Status;

import java.time.LocalDateTime;

@Data
public class BatteryPackDetail {
    private Double rsocMin;
    private Double rsocMax;
    private Double rsocAvg;
    private Double usocMin;
    private Double usocMax;
    private Double usocAvg;
    private Double chgPmax;
    private Double dchgPmax;
    private Double chgImax;
    private Double dchgImax;
    private Double cellVoltageMin;
    private Double cellVoltageMax;
    private Double cellVoltageDv;
    private Double cellTempMax;
    private Double cellTempMin;
    private Double cellTempAvg;
    private Status status;
    private LocalDateTime createAt;
    private String filterGroup;
}
