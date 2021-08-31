package com.edu.blog.controller.api;

import java.io.IOException;


import org.json.JSONException;
import org.json.JSONObject;
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

import com.edu.blog.model.OAuthToken;
import com.edu.blog.model.User;
import com.edu.blog.service.UserService;

@Controller
public class KakaoApiController {

	@Autowired
	UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	// redirect uri
	@GetMapping("/auth/kakao/callback")
	public String kakaoTokenRequest(String code) throws IOException, JSONException {

		// 액세스 토큰과 리프레시 토큰을 갱신하는 API입니다.

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

		ResponseEntity<String> response = rt.exchange(url, // 요청 url
				HttpMethod.POST, // 요청 방식
				tokenRequest, // 전송 데이터
				String.class); // 반환되는 데이터 타입

		JSONObject jsonObj = new JSONObject(response.getBody());
		OAuthToken token = new OAuthToken();

		token.setAccess_token(jsonObj.getString("access_token"));
		token.setToken_type(jsonObj.getString("token_type"));
		token.setToken_type(jsonObj.getString("refresh_token"));
		token.setExpires_in(jsonObj.getInt("expires_in"));
		token.setScope(jsonObj.getString("scope"));
		token.setRefresh_token_expires_in(jsonObj.getInt("refresh_token_expires_in"));

		
		kakaoProfileRequest(token);
		
		return "redirect:/";

	}

	public void kakaoProfileRequest(OAuthToken token) throws JSONException {

		// 현재 로그인한 사용자의 정보를 불러옵니다.

		String url = "https://kapi.kakao.com/v2/user/me";

		RestTemplate rt = new RestTemplate();
		HttpHeaders header = new HttpHeaders();

		header.add("Authorization", "Bearer " + token.getAccess_token());
		header.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

		HttpEntity<MultiValueMap<String, String>> profileRequest = new HttpEntity<>(header);

		ResponseEntity<String> response = rt.exchange(url, // 요청 url
				HttpMethod.POST, // 요청 방식
				profileRequest, // 전송 데이터
				String.class); // 반환되는 데이터 타입

		// User username, password, email

		JSONObject jsonObj = new JSONObject(response.getBody());

		String username = jsonObj.getJSONObject("kakao_account").getString("email") + "_" + jsonObj.getString("id");
		String email = jsonObj.getJSONObject("kakao_account").getString("email");
		String password = "cos1234";

		User kakaoUser = User.builder()
				.username(username)
				.email(email)
				.password(password)
				.oauth("kakao")
				.build();

		User user = userService.회원찾기(kakaoUser.getUsername());
		
		// 비가입자
		if (user.getUsername() == null) {
			userService.회원가입(kakaoUser);
		}

		// 로그인
		// 세션 등록
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), kakaoUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
	}

}
