package com.edu.blog.controller;

import com.edu.blog.model.User;
import com.edu.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DummyController {

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/dummy/join")
    public String join(User user) {

        System.out.println("id: " + user.getId());
        System.out.println("password: " + user.getPassword());
        System.out.println("email: " + user.getEmail());


        userRepository.save(user);

        return "회원가입이 완료되었습니다. ";
    }

}
