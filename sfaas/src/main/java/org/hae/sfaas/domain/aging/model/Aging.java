package org.hae.sfaas.domain.aging.model;

import lombok.Data;
import org.hae.sfaas.domain.welder.model.Status;

import java.time.LocalDateTime;

@Data
public class Aging {
    private Long agingQualityId;
    private Long factoryId;
    private Long ambientTmp;
    private Status status;
    private int rul;
    private LocalDateTime createAt;
}
