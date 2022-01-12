package com.edu.blog.controller;


import com.edu.blog.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("")
    public String index(Model model,
                        @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("boardList", boardService.글목록(pageable));
        // /WEB-INF/views/index.jsp
        return "index";
    }
    @GetMapping("/board/writeForm")
    public String writeForm() {
        // /WEB-INF/views/board/writeForm.jsp
        return "/board/writeForm";
    }
}
