package com.edu.blog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/auth/user/joinForm")
    public String joinForm() {
        // /WEB-INF/views/user/joinForm.jsp
        return "user/joinForm";
    }

    @GetMapping("/auth/user/loginForm")
    public String loginForm() {
        // /WEB-INF/views/user/loginForm.jsp
        return "user/loginForm";
    }

    @GetMapping("/user/updateForm")
    public String updateForm() {
        // /WEB-INF/views/user/updateForm.jsp
        return "user/updateForm";
    }

}
