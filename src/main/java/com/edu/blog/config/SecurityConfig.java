package com.edu.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.edu.blog.config.auth.PrincipalDetail;
// Bean 등록은 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것이다. 
import com.edu.blog.config.auth.PrincipalDetailService;

@Configuration	// IoC
@EnableWebSecurity	// Security Filter 
@EnableGlobalMethodSecurity(prePostEnabled = true)	// 특정 주소 접근 시 권한 및 인증을 미리 체크해준다. 
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private PrincipalDetailService principalDetailService;
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}

	@Bean	// IoC
	public BCryptPasswordEncoder encode() {
		
		// 비밀번호를 해쉬 암호화해준다. 
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encode());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.csrf().disable() // csrf 토큰 비활성화 
			.authorizeRequests()
				.antMatchers("/", "/auth/**", "/javascript/**", "/css/**", "/image/**", "/dummy/**")	// /auth 경로로 들어온 모든 요청은 누구나 허용된다.  
				.permitAll()
				.anyRequest()	// 그 외 요청은 인증되어야한다. 
				.authenticated()
			.and()
				.formLogin()
				.loginPage("/auth/loginForm")
				.loginProcessingUrl("/auth/login")	// 스프링 시큐리티가 해당 주소로 로그인을 가로채서 대신 로그인을 실행한다. 
				.defaultSuccessUrl("/");
				// .failureUrl("/fail");
			
	}

}
