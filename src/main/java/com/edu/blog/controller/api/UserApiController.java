package com.edu.blog.controller.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.edu.blog.dto.ResponseDto;

import com.edu.blog.model.User;
import com.edu.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/auth/join")
	public ResponseDto<Integer> insert(@RequestBody User user) {

		userService.회원가입(user);
		// Http 통신 상태를 출력해준다.
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}

	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) {

		userService.회원수정(user);
		// Service가 종료되는 시점에 트랜잭션이 종료되기 때문에 데이터베이스에 값은 변경이된다. 하지만 세션 값은 변경되지 않은 상태이기 때문에
		// 직접 세션 값을 변경해 주어야 한다.

		// 세션 등록
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);

	}

	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) throws IOException {
		
		// 액세스 토큰과 리프레시 토큰
		String url = "https://kauth.kakao.com/oauth/token";
		
		RestTemplate rt = new RestTemplate();
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("grant_type", "authorization_code");
		body.add("client_id", "784bf132b14053e60a1032b39c3818d2");
		body.add("redirect_uri", "http://localhost:8080/auth/kakao/callback");
		body.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(body, header);
		System.out.println(tokenRequest);
		
		
		ResponseEntity<String> response = rt.exchange(
				url,	// 요청 url
				HttpMethod.POST, // 요청 방식 
				tokenRequest,	// 전송 데이터 
				String.class);	// 반환되는 데이터 타입
		return response.getBody();
	
	}

}
