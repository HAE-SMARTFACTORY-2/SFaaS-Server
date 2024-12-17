package org.hae.sfaas.domain.board.model;

import lombok.Data;
import org.hae.sfaas.domain.board.dto.request.BoardRegisterDTO;

import java.time.LocalDateTime;

@Data
public class Board {
    private Long boardId;
    private Long userId;
    private String title;
    private String content;
    private LocalDateTime createAt;

    public Board(Long userId, BoardRegisterDTO boardRegisterDTO){
        this.userId = userId;
        this.title = boardRegisterDTO.title();
        this.content = boardRegisterDTO.content();
        this.createAt = LocalDateTime.now().minusYears(2);
    }
}
