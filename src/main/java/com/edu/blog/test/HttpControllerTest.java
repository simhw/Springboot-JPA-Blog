package com.edu.blog.test;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.web.bind.annotation.*;


// 사용자가 요청했을 때 데이터를 응답할 경우 @RestController를 명시해준다.
// 사용자가 요청했을 때 정적 파일을 응답할 경우 @Controller를 명시해준다.


@RestController
public class HttpControllerTest {


    // 웹 브라우저에서만 GET 요청이 가능하다.
    // GET 방식은 query request 방식으로만 데이터 전송이 가능하다.
    @GetMapping("/get/test")
    public String getTest(Member member) {
        System.out.println("id: " + member.getId());
        System.out.println("password : " + member.getPassword());
        return "GET Request";
    }

    @PostMapping("/post/test")
    public String postTest(@RequestBody Member member) {
        // MessageConverter 가 자동으로 Member 객체 형태로 매핑해준다.
        System.out.println("id: " + member.getId());
        System.out.println("password : " + member.getPassword());
        return "Post Request";
    }

    @PutMapping("/put/test")
    public Member putTest(@RequestBody Member member) {
        return member;
    }

    @DeleteMapping("/delete/test")
    public String deleteTest() {
        return "DELETE Request";
    }

    @GetMapping("/lombok/test")
    public String lombokTest() {

        Member member = Member.builder().id("simsim").password("11234").email("sim@naver.com").build();

        System.out.println("getter: " + member.getId());
        member.setId("sim");
        System.out.println("setter : " + member.getId());
        return "lombokTest!!";
    }
}
