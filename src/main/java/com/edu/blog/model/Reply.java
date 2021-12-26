package com.edu.blog.model;

import java.sql.Timestamp;
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


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	

	@Column(nullable = false, length = 200)
	private String content;


	// ** ORM **
	// Reply : Board = N : 1
	@ManyToOne	
	@JoinColumn(name = "board_id")
	private Board board;


	// ** ORM **
	// Reply : User = N : 1
	@ManyToOne 
	@JoinColumn(name = "user_id")
	private User user;



	@CreationTimestamp
	private Timestamp createDate;
	
	public void updateReply(String content, User user, Board board) {
		setContent(content);
		setUser(user);
		setBoard(board);
	}
}
