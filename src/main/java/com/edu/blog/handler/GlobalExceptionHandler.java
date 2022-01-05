package com.edu.blog.handler;


import com.edu.blog.dto.ResponseDto;
import org.hibernate.exception.DataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseDto<String> handleException(Exception ex) {
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }

    // 예외 처리마다 클래스 생성이 가능하다.
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseDto<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
}
