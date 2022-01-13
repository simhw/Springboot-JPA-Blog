package com.edu.blog.controller;


import com.edu.blog.model.Board;
import com.edu.blog.service.BoardService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.jws.WebParam;
import java.util.Optional;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("")
    public String index(Model model, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boardList", boardService.글목록(pageable));
        // /WEB-INF/views/index.jsp
        return "index";
    }

    @GetMapping("/board/{id}")
    public String getBoard(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.글상세보기(id));
        // /WEB-INF/views/board/index.jsp
        return "board/detail";
    }

    @GetMapping("/board/writeForm")
    public String writeForm() {
        // /WEB-INF/views/board/writeForm.jsp
        return "/board/writeForm";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable int id, Model model) {
        model.addAttribute("board", boardService.글상세보기(id));
        // /WEB-INF/views/board//updateForm.jsp
        return "/board/updateForm";
    }
}
