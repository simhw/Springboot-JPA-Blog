package com.edu.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 데이터가 아닌 파일을 반환해준다.
@Controller
public class TempControllerTest {

    @GetMapping("/temp/static")
    public String tempFile() {

        System.out.println("HTML File Return");

        // Spring 기본 경로인 src/main/resources/static 내 모든 정적 파일을 반환한다.
        // 하지만 JSP 파일은 정적 파일이 아니므로 JSP 템플릿 엔진이 필요하다.

        return "/test_static.html";
    }

// /WEB/views/
//    spring:
//      mvc:
//        view:
//          prefix: /WEB-INF/views/
//          suffix: .jsp

    @GetMapping("/temp/jsp")
    public String tempJSP() {

        System.out.println("JSP File Return");
        // /WEB-INF/views/(file_name).jsp
        return "test_jsp";
    }

}
