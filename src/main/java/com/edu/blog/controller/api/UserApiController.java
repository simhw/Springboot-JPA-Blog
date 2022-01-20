package com.edu.blog.controller.api;


import com.edu.blog.config.auth.PrincipalDetail;
import com.edu.blog.dto.ResponseDto;
import com.edu.blog.model.KakaoToken;
import com.edu.blog.model.User;
import com.edu.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // 회원가입
    @PostMapping("/auth/api/user/join")
    public ResponseDto<String> join(@RequestBody User user) {
        userService.회원가입(user);
        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");
    }

    // 회원수정
    @PutMapping("/api/user")
    public ResponseDto<String> updateUser(@RequestBody User user) {
        userService.회원수정(user);
        // AuthenticationManager 를 통해 Authentication 객체 생성 후
        // Session 에 변경된 회원 정보를 저장해준다.
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");
    }

    // 카카오 로그인
    @GetMapping("/auth/kakao/callback")
    public ResponseEntity kakaoRedirect(String code) throws JsonProcessingException {
        // 1. 카카오에서 인증 코드 반환해준다.

        // 2. 카카오에 인증 토큰을 요청한다.
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> tokenReqparams = new LinkedMultiValueMap<>();
        tokenReqparams.add("grant_type", "authorization_code");
        tokenReqparams.add("client_id", "784bf132b14053e60a1032b39c3818d2");
        tokenReqparams.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
        tokenReqparams.add("code", code);

        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(tokenReqparams, headers);
        // 3. 카카오에서 토큰을 받는다.
        ResponseEntity<String> response = template.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST, tokenRequest, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        KakaoToken kakaoToken = objectMapper.readValue(response.getBody(), KakaoToken.class);

        // 4. 토큰을 전송하여 카카오에 사용자 정보를 요청한다.
        headers.add("Authorization", "Bearer " + kakaoToken.getAccess_token());
        HttpEntity<MultiValueMap<String, String>> userInfoRequest = new HttpEntity<>(headers);

        response = template.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, userInfoRequest, String.class);
        return response;
    }

    // 로그인 구현
    @PostMapping("/api/user/login")
    public ResponseDto<String> login(@RequestBody User user, HttpSession session) {
        // ResponseEntity 추가 구현 예정
        User principal = userService.로그인(user);
        if (principal != null) {
            session.setAttribute("principal", principal);
        }
        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");
    }
}
