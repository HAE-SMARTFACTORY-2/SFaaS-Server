package org.hae.sfaas.domain.factory.model;

import lombok.Data;

@Data
public class Factory {
    private Long factoryId;
    private String factoryCode;
    private String factoryLocation;
}