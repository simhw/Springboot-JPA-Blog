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

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseDto<String> handleArgumentException(IllegalArgumentException ex) {
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
    }
}
