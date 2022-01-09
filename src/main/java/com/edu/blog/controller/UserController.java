package com.edu.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/auth/user/joinForm")
    public String joinForm() {

        // /WEB-INF/views/blog/user/joinForm.jsp
        return "user/joinForm";
    }

    @GetMapping("/auth/user/loginForm")
    public String loginForm() {

        // /WEB-INF/views/blog/user/loginForm.jsp
        return "user/loginForm";
    }
}
