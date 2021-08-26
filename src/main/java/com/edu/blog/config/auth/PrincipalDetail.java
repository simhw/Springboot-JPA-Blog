package com.edu.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.edu.blog.model.User;

import lombok.Getter;

// 스프링 시큐리티의 세션 저장소에 저장할 클래스이다. 


@Getter
public class PrincipalDetail implements UserDetails{
	private User user;	// composition
	
	public PrincipalDetail(User user) {
		super();
		this.user = user;
	}

	@Override
	public String getPassword() {
		return user.getPassword();	
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	// 계정이 만료되지 않았는지 반환한다. (false, 만료)
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 비밀번호가 만료되지 않았는지 반환한다. 
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 계정 활성화 상태인지(사용 가능) 반환한다. 
	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
	// 계정이 갖고있는 권한 목록을 반환해준다. (권한이 다수일 수 있다.)
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {	
				return "ROLE_" + user.getRole(); 	// ROLE_USER
			}
		});
		
//		collectors.add(()-> {
//			return "ROLE_" + user.getRole();
//			}
//		);
		
		return collectors;
	}
}
