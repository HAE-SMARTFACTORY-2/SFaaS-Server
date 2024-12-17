package org.hae.sfaas.domain.board.dto.response;

import org.hae.sfaas.domain.board.model.BoardInfo;

import java.time.LocalDateTime;

public record BoardInfoResponse (
        Long boardId,
        String userName,
        String title,
        LocalDateTime createAt
){
    public static BoardInfoResponse of(BoardInfo boardInfo){
        return new BoardInfoResponse(
                boardInfo.getBoardId(),
                boardInfo.getUserName(),
                boardInfo.getTitle(),
                boardInfo.getCreateAt()
        );
    }
}
