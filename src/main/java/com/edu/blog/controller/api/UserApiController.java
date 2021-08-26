package com.edu.blog.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
		// Service가 종료되는 시점에 트랜잭션이 종료되기 때문에 데이터베이스에 값은 변경이된다. 하지만 세션 값은 변경되지 않은 상태이기 때문에 직접 세션 값을 변경해 주어야 한다. 
		

		// 세션 등록 
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
						
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	


	
	
}
