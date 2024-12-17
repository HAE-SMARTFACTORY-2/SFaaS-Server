package org.hae.sfaas.domain.board.model;

import lombok.Data;

@Data
public class BoardRegister {
    private Long factoryId;
    private String title;
    private String content;
}
