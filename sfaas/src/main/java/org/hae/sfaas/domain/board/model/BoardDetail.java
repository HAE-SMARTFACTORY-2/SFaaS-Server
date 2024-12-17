package org.hae.sfaas.domain.board.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDetail {
    private String userName;
    private String title;
    private String content;
    private LocalDateTime createAt;
}
