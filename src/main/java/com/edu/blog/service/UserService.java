package com.edu.blog.service;

import com.edu.blog.model.RoleType;
import com.edu.blog.model.User;
import com.edu.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //    bcrypt 해쉬 알고리즘
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional
    public User 회원찾기(User user) {
        User joinedUser = userRepository.findUserById(user.getId()).orElseGet(() -> {
            return new User();
        });
        return joinedUser;
    }

    @Transactional
    public void 회원가입(User user) {
        String password = user.getPassword();
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
}

    @Transactional
    public void 회원수정(User req) {
        // 영속화
        User user = userRepository.findUserById(req.getId()).orElseThrow(() -> {
            // Transaction 처리 (Rollback)
            return new IllegalArgumentException("존재하지 않은 회원입니다.");
        });
        String encodedPassword = encoder.encode(req.getPassword());
        user.setPassword(encodedPassword);
        user.setEmail(req.getEmail());
    }

    // Service 종료 시에도 데이터의 정합성이 유지된다.
    @Transactional(readOnly = true)
    public User 로그인(User user) {
        return userRepository.findByIdAndPassword(user.getId(), user.getPassword());
    }

}
