package com.edu.blog.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.edu.blog.dto.ResponseDto;


@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	// Exception이 발생하면 GlobalExceptionHandler이 실행된다.
	
	@ExceptionHandler(value = IllegalArgumentException.class)
	public ResponseDto<String> handleArgumentException(IllegalArgumentException ex) {

		return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
	}

}
