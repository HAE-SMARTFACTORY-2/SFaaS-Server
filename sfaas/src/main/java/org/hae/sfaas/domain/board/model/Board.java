package org.hae.sfaas.domain.board.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Board {
    private Long boardId;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime createAt;

    public Board(Long userId, BoardRegister boardRegister){
        this.userId = userId;
        this.title = boardRegister.getTitle();
        this.content = boardRegister.getContent();
        this.createAt = LocalDateTime.now().minusYears(2);
    }
}
