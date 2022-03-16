package com.edu.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("/chat/chatForm")
    public String chatForm() {
        return "chat/chatForm";
    }

}


