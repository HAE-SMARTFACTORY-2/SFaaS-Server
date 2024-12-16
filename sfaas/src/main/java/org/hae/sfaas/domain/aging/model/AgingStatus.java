package org.hae.sfaas.domain.aging.model;

import lombok.Data;

@Data
public class AgingStatus {
    private int okCount;
    private int ngCount;
    private int totalCount;
    private Long temp;
    private String filterGroup;
}
