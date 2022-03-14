package com.edu.blog.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@Column(nullable = false, length = 100) 
	private String title;
	
	// 내용 (대용량 데이터)
	@Lob
	private String content; 
	
	// 조회수 (기본 값 정수형)
	private int count;


	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_idx")
	private User user;

	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")
	private List<Reply> replies;

	@CreationTimestamp
	private Timestamp createDate;
}
