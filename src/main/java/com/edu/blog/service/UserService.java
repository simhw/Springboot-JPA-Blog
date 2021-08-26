package com.edu.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.blog.model.RoleType;
import com.edu.blog.model.User;
import com.edu.blog.repository.UserRepository;

// 트랜잭션을 관리해준다.
// 다수의 기능들(CRUD)을 하나의 서비스로 처리하기 위해 사용된다. 

// Inversion of Control

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	
	@Transactional
	public void 회원가입(User user) {
		
		// 해쉬 비밀번호 
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);	
		
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
	}


	@Transactional
	public void 회원수정(User user) {
		
		// 영속성 컨텍스트 User 인스턴스를 영속화시키고, 영속화된 User 인스턴스를 수정한다. 	
		User persistance = userRepository.findById(user.getId()).orElseThrow(() ->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		// 해쉬 비밀번호 
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);	
		
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		
		// 회원수정 종료 = Service 종료 = Transaction 종료 = Commit 자동 수행
		// 영속화된 persistance 객체의 변화가 감지되면 update문을 자동으로 수행한다. 
		
		
	}
	
	
	
	
	
	
	
	
	


	

//	// SELECT할 때 Transaction이 시작되고, 서비스 종료 시점에 Transaction이 종료된다. 
//	@Transactional(readOnly = true)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
}
