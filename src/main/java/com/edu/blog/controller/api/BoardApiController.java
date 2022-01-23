package com.edu.blog.controller.api;

import com.edu.blog.config.auth.PrincipalDetail;
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
        System.out.println("start deleteBoard!!!");
        boardService.글삭제(id);
        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");
    }

    @PutMapping("/api/board/{id}")
    public ResponseDto<String> updeteBoard(@PathVariable int id, @RequestBody Board board) {
        boardService.글수정(id, board);
        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");
    }

    @PostMapping("/api/board/{boardId}/reply")
    public ResponseDto<String> writeReply(@PathVariable int boardId, @RequestBody Reply reply, @AuthenticationPrincipal PrincipalDetail principal) {
        System.out.println(reply.getContent());
        boardService.댓글쓰기(boardId, principal.getUser(), reply.getContent());
        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");
    }
}
