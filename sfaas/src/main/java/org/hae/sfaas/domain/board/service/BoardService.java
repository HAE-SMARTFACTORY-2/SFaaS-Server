package org.hae.sfaas.domain.board.service;

import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.board.dto.response.BoardGroupResponse;
import org.hae.sfaas.domain.board.dto.response.BoardInfoResponse;
import org.hae.sfaas.domain.board.mapper.BoardMapper;
import org.hae.sfaas.domain.board.model.Board;
import org.hae.sfaas.domain.board.model.BoardDetail;
import org.hae.sfaas.domain.board.model.BoardInfo;
import org.hae.sfaas.domain.board.model.BoardRegister;
import org.hae.sfaas.domain.user.mapper.UserMapper;
import org.hae.sfaas.domain.user.model.User;
import org.hae.sfaas.domain.user.model.UserRole;
import org.hae.sfaas.global.common.exception.SFaaSException;
import org.hae.sfaas.global.common.response.ErrorType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;
    private final UserMapper userMapper;

    public void createBoard(Long userId, BoardRegister boardRegister){
        User user = userMapper.findById(userId);

        if(user == null){
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }else if(!Objects.equals(user.getUserRole().getRole(), UserRole.ADMIN.getRole())
                || !Objects.equals(user.getUserRole().getRole(), UserRole.MANAGER.getRole())){
            throw new SFaaSException(ErrorType.FORBIDDEN_USER);
        }

        Board board = new Board(userId,boardRegister);

        if(boardMapper.createBoard(board) < 1){
            throw new SFaaSException(ErrorType.INTERNAL_SERVER);
        }
    }

    public  List<BoardInfoResponse> getAllBoard(Long userId,Long factoryId){
        User user = userMapper.findById(userId);

        if(user == null){
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }

        Long fId = null;
        if (user.getUserRole().equals(UserRole.ADMIN) && factoryId != null) {
            fId = factoryId;
        } else {
            fId = user.getFactoryId();
        }

        List<BoardInfo> boardInfos = boardMapper.getBoardInfo(factoryId);
        return boardInfos.stream().map(BoardInfoResponse::of).toList();
    }

    public BoardDetail getBoardDetail(Long boardId){
        return boardMapper.getBoardDetail(boardId);
    }

    public void deleteBoard(Long userId,Long boardId){
        User user = userMapper.findById(userId);

        if(user == null){
            throw new SFaaSException(ErrorType.NOT_FOUND_USER);
        }else if(!Objects.equals(user.getUserRole().getRole(), UserRole.ADMIN.getRole())
                || !Objects.equals(user.getUserRole().getRole(), UserRole.MANAGER.getRole())){
            throw new SFaaSException(ErrorType.FORBIDDEN_USER);
        }

        boardMapper.deleteBoard(boardId);
    }
}
