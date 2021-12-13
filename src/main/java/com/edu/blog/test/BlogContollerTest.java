package com.edu.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Spring은 패키지 이하의 모든 파일을 스캔하는 것이 아닌
// 특정 어노테이션이 붙은 클래스만 SpringContainer 에서 관리한다.(IoC)

@RestController
public class BlogContollerTest {
    @GetMapping("/test/hello")
    public String returnHello(){
        String html = "<h1>Hello World</h1>";
        return html;
    }
}
