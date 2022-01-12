package com.edu.blog.config.auth;

import com.edu.blog.model.RoleType;
import com.edu.blog.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// implements 은 부모의 메소드를 반드시 오버라이딩(재정의)해야 한다.
// UserDetails 타입
public class PrincipalDetail implements UserDetails {

    // Composition
    private User user;

    public PrincipalDetail(User user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getId();
    }

    // 계정이 만료되지 않았는지 반환한다. (false: 만료)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있는지 반환한다. (false: 잠김)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료되지 않았는지 반환한다. (false: 만료)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화 상태인지 반환한다. (false: 비활성화)
    @Override
    public boolean isEnabled() { return true; }

    // 계정의 권한 목록을 반환한다.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return "ROLE_" + user.getRole();    //ex. ROLE_USER, ROLE_ADMIN
            }
        });
        return collection;
    }
}
