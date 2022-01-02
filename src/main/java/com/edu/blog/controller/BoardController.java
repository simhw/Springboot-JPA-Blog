package com.edu.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping("")
    public String index() {

        // /WEB-INF/views/blog/index.jsp
        return "index";
    }
}
