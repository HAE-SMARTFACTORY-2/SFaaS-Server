package org.hae.sfaas.domain.board.mapper;

import org.hae.sfaas.domain.board.model.Board;
import org.hae.sfaas.domain.board.model.BoardDetail;
import org.hae.sfaas.domain.board.model.BoardInfo;

import java.util.List;

public interface BoardMapper {
    int createBoard(Board board);
    List<BoardInfo> getBoardInfo(Long factoryId);
    BoardDetail getBoardDetail(Long boardId);
    int deleteBoard(Long boardId);
}
