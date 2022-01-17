package com.edu.blog.controller.api;


import com.edu.blog.config.auth.PrincipalDetail;
import com.edu.blog.dto.ResponseDto;
import com.edu.blog.model.User;
import com.edu.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    //    bcrypt 해쉬 알고리즘
    @Autowired
    private BCryptPasswordEncoder encoder;


    @PostMapping("/auth/api/user/join")
    public ResponseDto<String> join(@RequestBody User user) {
        userService.회원가입(user);
        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");
    }

    @PutMapping("/api/user")
    public ResponseDto<String> updateUser(@RequestBody User user) {
        userService.회원수정(user);
        // Authentication 객체 생성 후
        // Session 에 변경된 회원 정보를 저장해준다.
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");
    }

    @PostMapping("/auth/api/user/login")
    public ResponseDto<String> login(@RequestBody User user) {
        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");
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
