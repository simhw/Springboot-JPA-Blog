package com.edu.blog.controller.api;

import com.edu.blog.config.auth.PrincipalDetail;
import com.edu.blog.dto.ResponseDto;
import com.edu.blog.model.Board;
import com.edu.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardApiController {

    @Autowired
    BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<String> writeBoard(@RequestBody Board board,
                                          @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.글쓰기(board, principal.getUser());
        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");
    }
}
