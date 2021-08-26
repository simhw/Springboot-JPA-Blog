package com.edu.blog.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDto<T> {
	
	
	// UserApiController UserService 
	// Data Transfer Object
	
	int status;
	T data;
	
}
