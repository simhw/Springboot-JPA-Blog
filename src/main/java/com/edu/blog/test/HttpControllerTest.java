package com.edu.blog.test;

import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;


// 사용자가 요청했을 때 데이터를 응답할 경우 @RestController를 명시해준다.
// 사용자가 요청했을 때 정적 파일을 응답할 경우 @Controller를 명시해준다.


@RestController
public class HttpControllerTest {
    // 웹 브라우저에서만 GET 요청이 가능하다.
    @GetMapping("/http/get/test")
    public String getTest() {
        return "GET Request";
    }

    @PostMapping("/http/post/test")
    public String postTest() {
        return "POST Request";
    }

    @PutMapping("/http/put/test")
    public String putTeest() {
        return "PUT Request";
    }

    @DeleteMapping("/http/delete/test")
    public String deleteTest() {
        return "DELETE Request";
    }
}
