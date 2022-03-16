package com.edu.blog.controller;


import com.edu.blog.model.KakaoToken;
import com.edu.blog.model.KakaoUser;
import com.edu.blog.model.User;
import com.edu.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class UserController {
    private static final String PASSWORD = "kakao";

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

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

    @GetMapping("/user/chatForm")
    public String chatForm() {
        // /WEB-INF/views/user/updateForm.jsp
        return "user/chatForm";
    }

    // 카카오 로그인 및 회원가입
    @GetMapping("/auth/kakao/callback")
    public String kakaoRedirect(String code) throws JsonProcessingException {
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
        KakaoUser kakaoUser = objectMapper.readValue(response.getBody(), KakaoUser.class);

        String userId = kakaoUser.getId().toString();
        String userEmail = kakaoUser.getKakao_account().getEmail();
        User user = User.builder().id(userId).password(PASSWORD).email(userEmail).oauth("kakao").build();

        // 5. 기존 회원 확인 후 회원가입을 한다.
        User joinedUser = userService.회원찾기(user);
        if (joinedUser.getId() == null) {
            userService.회원가입(user);
        }
        // 6. 로그인
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getId(), PASSWORD));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/";
    }

}
