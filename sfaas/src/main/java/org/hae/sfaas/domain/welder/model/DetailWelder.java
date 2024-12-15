package org.hae.sfaas.domain.welder.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DetailWelder {
    private Long welderId;
    private String factoryCode;
    private String factoryLocation;
    private Long speed;
    private Long length;
    private Long realPower;
    private Long setDuty;
    private Long setPower;
    private Long gateOnTime;
    private LocalDateTime createAt;
    private Status status;
}
