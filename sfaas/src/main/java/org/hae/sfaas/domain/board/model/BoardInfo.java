package org.hae.sfaas.domain.board.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardInfo {
    Long boardId;
    String userName;
    String title;
    String content;
    LocalDateTime createAt;
}
