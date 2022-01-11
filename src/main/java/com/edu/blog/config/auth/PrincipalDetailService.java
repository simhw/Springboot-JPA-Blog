package com.edu.blog.config.auth;

import com.edu.blog.model.User;
import com.edu.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class PrincipalDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    // 유효성
    // ID 가 데이터베이스에 있는지 확인해준다.
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User principal = userRepository.findUserById(id).orElseThrow(()->{
            return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. " + id);
        });
        return new PrincipalDetail(principal);
    }
}
