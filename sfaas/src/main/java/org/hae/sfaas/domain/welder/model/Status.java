package org.hae.sfaas.domain.welder.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Status {
    NONE("NONE"),
    OK("OK"),
    NG("NG");

    private String staus;
}
