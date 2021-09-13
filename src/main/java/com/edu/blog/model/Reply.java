package com.edu.blog.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor	// 기본 생성자 
@AllArgsConstructor	// 모든 변수의 생성자 
@Builder
@Data
@Entity // Reply 클래스가 MySQL 테이블에 생성된다
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // PK
	
	@Column(nullable = false, length = 200)
	private String content;
	
	// Reply : Board = n : 1
	@ManyToOne	
	@JoinColumn(name = "boardId")
	private Board board; // FK
	
	
	// Reply : User = n : 1
	@ManyToOne 
	@JoinColumn(name = "userId")
	private User user;
	
	@CreationTimestamp
	private LocalDateTime createDate;
	
	public void updateReply(String content, User user, Board board) {
		setContent(content);
		setUser(user);
		setBoard(board);
	}
}
