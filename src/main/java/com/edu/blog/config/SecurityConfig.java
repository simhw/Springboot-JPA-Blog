package com.edu.blog.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// Spring Security 설정 파일

// Bean 등록(IoC)
@Configuration
// Security Filter 등록
@EnableWebSecurity
// 특정 주소로 접근 권한 및 인증을 미리 확인해준다.
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable() // csrf 토큰 비활성화
                .authorizeRequests()
                .antMatchers("/", "/auth/**", "/javascript/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().formLogin().loginPage("/auth/user/loginForm");
    }
}
