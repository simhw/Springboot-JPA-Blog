package com.edu.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

// @Getter
// @Setter
@Data
@AllArgsConstructor
@Builder
public class Member {
    // 변수는 항상 private 접근 제한자를 사용하고
    // 함수를 통해서 변수 값을 변경해준다.

    private  int idx;
    private String id;
    private String password;
    private String email;





}
