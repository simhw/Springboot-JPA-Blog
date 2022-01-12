package com.edu.blog.config;


import com.edu.blog.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

    @Autowired
    private PrincipalDetailService principalDetailService;

    // BCryptPasswordEncoder 는 Spring Security 에서 제공하는 비밀번호 암호화 객체이다.
    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(encodePassword());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable() // csrf 토큰 비활성화
                .authorizeRequests()
                .antMatchers("/", "/auth/**", "/javascript/**", "/image/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().loginPage("/auth/user/loginForm")
                .loginProcessingUrl("/auth/api/user/login") // Spring Security Filter 가 해당 주소로 오는 요청을 가로챈다.
                .defaultSuccessUrl("/"); // 성공
    }
}
