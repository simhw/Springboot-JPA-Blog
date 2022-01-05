package com.edu.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Getter + Setter 로 접근한다.
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto<T> {
    int err_cd;
    T err_msg;
}
