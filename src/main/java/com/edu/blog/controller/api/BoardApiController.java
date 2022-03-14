package com.edu.blog.controller.api;

import com.edu.blog.config.auth.PrincipalDetail;
import com.edu.blog.dto.ReplyDto;
import com.edu.blog.dto.ResponseDto;
import com.edu.blog.model.Board;
import com.edu.blog.model.Reply;
import com.edu.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardApiController {

    @Autowired
    BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<String> writeBoard(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.글쓰기(board, principal.getUser());
        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<String> deleteBoard(@PathVariable int id) {
        boardService.글삭제(id);
        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<String> updeteBoard(@PathVariable int id, @RequestBody Board board) {
        boardService.글수정(id, board);
        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");
    }

    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<String> writeReply(@RequestBody ReplyDto replyDto, @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.댓글쓰기(replyDto, principal.getUser());
        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");
    }

    @DeleteMapping("/api/board/{boardId}/reply/{replyId}")
    public ResponseDto<String> deleteReply(@PathVariable int boardId, @PathVariable int replyId) {
        boardService.댓글삭제(replyId);
        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");
    }
}
