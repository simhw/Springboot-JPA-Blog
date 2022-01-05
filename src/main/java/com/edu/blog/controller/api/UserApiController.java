package com.edu.blog.controller.api;


import com.edu.blog.dto.ResponseDto;
import com.edu.blog.model.RoleType;
import com.edu.blog.model.User;
import com.edu.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @PostMapping("/api/user")
    public ResponseDto<String> join(@RequestBody User user) {

        user.setRole(RoleType.USER);
        userService.회원가입(user);

        return new ResponseDto<String>(HttpStatus.OK.value(), "성공!!!");

    }
}
