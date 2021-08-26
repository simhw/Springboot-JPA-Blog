package com.edu.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.blog.dto.ResponseDto;
import com.edu.blog.model.RoleType;
import com.edu.blog.model.User;
import com.edu.blog.service.UserService;

@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/join")
	public ResponseDto<Integer> insert(@RequestBody User user) {
		
		userService.회원가입(user);
		// Http 통신 상태를 출력해준다. 	
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);		
	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) {	
		
		userService.회원수정(user);
		// Service가 종료되는 시점에 트랜잭션이 종료되기 때문에 데이터베이스에 값은 변경이되었다.
		// 하지만 세션 값은 변경되지 않은 상태이기 때문에 직접 세션 값을 변경해 주어야 한다. 
		
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> select(@RequestBody User user, HttpSession session) {
//		User principal = userService.로그인(user);		// principal, 접근주체 
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);		
//	}
	
}
