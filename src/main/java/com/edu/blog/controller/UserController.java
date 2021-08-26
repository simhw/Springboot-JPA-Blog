package com.edu.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



// controller
// 페이지(html)를 반환하며 View Resolver가 작동한다. 

@Controller
public class UserController {
	
	
	// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/로 허용해준다. 
	
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {

		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		
		return "user/loginForm";
	}
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		
		return "user/updateForm";
		
	}
	
}
