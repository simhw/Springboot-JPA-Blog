package com.edu.blog.controller.api;


import com.edu.blog.dto.ResponseDto;
import com.edu.blog.model.RoleType;
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

    @Autowired HttpSession session;

    @PostMapping("/api/user/join")
    public ResponseDto<String> join(@RequestBody User user) {

        user.setRole(RoleType.USER);
        userService.회원가입(user);

        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");
    }

    @PostMapping("/api/user/login")
    public ResponseDto<String> login(@RequestBody User user) {
        User principal = userService.로그인(user);
        if (principal != null) {
            session.setAttribute("principal", principal);
        }
        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");

    }
    @PostMapping("/api/user/login2")
    public ResponseEntity<String> login2(@RequestBody User user) {
        User principal = userService.로그인(user);

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        ResponseDto<String> message = new ResponseDto<>(HttpStatus.OK.value(), "로그인 성공!!!");
        if (principal != null) {
            session.setAttribute("principal", principal);
            return new ResponseEntity(message, headers, HttpStatus.OK);
        }
        else{
            return new ResponseEntity(message, headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
