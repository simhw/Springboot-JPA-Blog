package com.edu.blog.controller.api;


import com.edu.blog.dto.ResponseDto;
import com.edu.blog.model.User;
import com.edu.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/api/user/join")
    public ResponseDto<String> join(@RequestBody User user) {
        System.out.println("UserApiController join start!!!");
        userService.회원가입(user);
        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");
    }

    @PostMapping("/auth/api/user/login")
    public ResponseDto<String> login(@RequestBody User user) {
        return null;
    }

    @PostMapping("/api/user/login")
    public ResponseDto<String> login2(@RequestBody User user, HttpSession session) {
        // ResponseEntity 추가 구현 예정
        User principal = userService.로그인2(user);
        if (principal != null) {
            session.setAttribute("principal", principal);
        }
        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");
    }
}
