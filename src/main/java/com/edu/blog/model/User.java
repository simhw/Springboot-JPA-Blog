package com.edu.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor	// 기본 생성자 
@AllArgsConstructor	// 모든 변수의 생성자 
@Builder
@Entity // User 클래스가 MySQL 테이블에 생성된다.
// @DynamicInsert // insert시 null인 필드를 제외시켜준다. 
public class User {
	
	@Id 	// Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	// 데이터베이스의 전략을 따라간다.
	private int id; 	//auto_increment
	
	@Column(nullable = false, length = 100, unique = true)
	private String username; 
	
	@Column(nullable = false, length = 100) 
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	//@ColumnDefault("'user'") // varchar
	// 데이터베이스에는 RoleType이 존재하지 않기 때문에 string 타입을 명시해준다.  
	@Enumerated(EnumType.STRING)
	private RoleType role; 
	

	private String oauth;
	
	@UpdateTimestamp // 현재 시간 자동 입력해준다. 
	private LocalDateTime createdTime;
	
}