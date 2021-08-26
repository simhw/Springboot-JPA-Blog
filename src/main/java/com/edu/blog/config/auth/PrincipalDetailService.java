package com.edu.blog.config.auth;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edu.blog.model.User;
import com.edu.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	
	@Override // username이 데이터베이스에 있는지 확인한다.
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User principal = userRepository.findByUsername(username).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				
				return new IllegalArgumentException("사용자가 존재하지 않습니다.");
			}
		});
		return new PrincipalDetail(principal);	// 스프링 시큐리티 세션에 User 정보가 저장된다. 
	}

}
