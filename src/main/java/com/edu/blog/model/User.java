package com.edu.blog.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
// 기본 생성자
@NoArgsConstructor
// 모든 변수의 생성자
@AllArgsConstructor
@Builder
// User 클래스가 MySQL 테이블에 생성된다.
@Entity
// @DynamicInsert // insert시 null인 필드를 제외시켜준다. 
public class User {

	// Primary Key
	@Id
	// 애플리케이션에 연결된 데이터베이스의 전략(auto_increment)을 따라간다.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 100, unique = true)
	private String username; 
	
	@Column(nullable = false, length = 100) 
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
	// @ColumnDefault("'user'") // varchar
	// 데이터베이스에는 RoleType 이 존재하지 않기 때문에 String 타입을 명시해준다.
	@Enumerated(EnumType.STRING)
	private RoleType role; 

	private String oauth;

	// 현재 시간 자동 입력해준다.
	@CreationTimestamp
	// private LocalDateTime createdDate;
	private Timestamp createdDate;
	
}