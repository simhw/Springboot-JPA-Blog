package com.edu.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("")
    public String index() {
        // /WEB-INF/views/index.jsp
        return "index";
    }
    @GetMapping("/board/writeForm")
    public String writeForm() {
        // /WEB-INF/views/board/writeForm.jsp
        return "/board/writeForm";
    }
}
