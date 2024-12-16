package org.hae.sfaas.domain.welder.model;

import lombok.Data;

@Data
public class WelderPower {
    private Double speed;
    private Double realPower;
    private Double setPower;
    private String filterGroup;
}
