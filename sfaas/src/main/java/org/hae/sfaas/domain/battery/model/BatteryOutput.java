package org.hae.sfaas.domain.battery.model;

import lombok.Data;

@Data
public class BatteryOutput {
    private Long factoryId;
    private int totalCount;
    private String filterGroup;
}