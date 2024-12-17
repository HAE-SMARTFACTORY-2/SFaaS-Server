package org.hae.sfaas.domain.board.dto.request;

public record BoardRegisterDTO(
    Long factoryId,
    String title,
    String content
){
}
