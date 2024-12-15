package org.hae.sfaas.domain.welder.model;

import lombok.Data;

@Data
public class WelderStatus {
    private Double speed;
    private String category;
    private int totalCount;
    private int okCount;
    private int ngCount;
    private String filterGroup;
}
