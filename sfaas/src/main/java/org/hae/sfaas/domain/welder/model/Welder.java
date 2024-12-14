package org.hae.sfaas.domain.welder.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Welder {

    private Long welderId;
    private Long factoryId;
    private Long speed;
    private Long length;
    private Long realPower;
    private Long setDuty;
    private Long setPower;
    private LocalDateTime gateOnTime;
    private LocalDateTime workingTime;
    private Status status;

}