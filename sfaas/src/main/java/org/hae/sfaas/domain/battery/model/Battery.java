package org.hae.sfaas.domain.battery.model;

import lombok.Data;
import org.hae.sfaas.domain.welder.model.Status;

import java.time.LocalDateTime;

@Data
public class Battery {
    private Long factoryId;
    private Status status;
    private LocalDateTime createAt;
}