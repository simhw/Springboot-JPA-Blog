package com.edu.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user/joinForm")
    public String joinForm() {

        // /WEB-INF/views/user/joinForm.jsp
        return "user/joinForm";
    }

    @GetMapping("/user/loginForm")
    public String loginForm() {

        // /WEB-INF/views/user/loginForm.jsp
        return "user/loginForm";
    }
}
