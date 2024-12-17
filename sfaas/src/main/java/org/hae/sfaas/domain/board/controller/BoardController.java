package org.hae.sfaas.domain.board.controller;

import lombok.RequiredArgsConstructor;
import org.hae.sfaas.domain.board.dto.request.BoardRegisterDTO;
import org.hae.sfaas.domain.board.service.BoardService;
import org.hae.sfaas.global.common.response.SFaaSResponse;
import org.hae.sfaas.global.common.response.SuccessType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board")
    public ResponseEntity<SFaaSResponse<?>> createBoard(@RequestHeader("userId") Long userId,
                                                        @RequestBody BoardRegisterDTO boardRegisterDTO){
        boardService.createBoard(userId,boardRegisterDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(SFaaSResponse.success(SuccessType.POST_BOARD_CREATE_SUCCESS));
    }

    @GetMapping("/board")
    public ResponseEntity<SFaaSResponse<?>> getAllBoard(@RequestHeader("userId") Long userId,
                                                        @RequestParam(value = "factoryId", required = false) Long factoryId){
        return ResponseEntity.status(HttpStatus.OK).body((SFaaSResponse.success(SuccessType.GET_BOARD_STATUS_SUCCESS,boardService.getAllBoard(userId,factoryId))));
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<SFaaSResponse<?>> getBoardId(@RequestHeader("userId") Long userId,
                                                       @PathVariable Long boardId){
        return ResponseEntity.status(HttpStatus.OK).body((SFaaSResponse.success(SuccessType.GET_BOARD_DETAIL_SUCCESS,boardService.getBoardDetail(boardId))));
    }

    @DeleteMapping("/board/{boardId}")
    public ResponseEntity<SFaaSResponse<?>> deleteBoard(@RequestHeader("userId") Long userId,
                                                        @PathVariable Long boardId){
        boardService.deleteBoard(userId,boardId);
        return ResponseEntity.status(HttpStatus.OK).body((SFaaSResponse.success(SuccessType.DELETE_BOARD_SUCCESS)));
    }
}
