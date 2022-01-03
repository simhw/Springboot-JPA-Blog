package com.edu.blog.controller.api;


import com.edu.blog.dto.ResponseDto;
import com.edu.blog.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @PostMapping("/api/user")
    public int join(@RequestBody User user) {

        System.out.println("id: " + user.getId());
        System.out.println("password: " + user.getPassword());
        System.out.println("email: " + user.getEmail());

        return 200;

    }

}
