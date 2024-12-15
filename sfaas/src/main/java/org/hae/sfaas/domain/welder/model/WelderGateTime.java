package org.hae.sfaas.domain.welder.model;

import lombok.Data;

@Data
public class WelderGateTime {
    private Double speed;
    private Double averageLength;
    private Double averageGateOnTime;
    private String filterGroup;
}
