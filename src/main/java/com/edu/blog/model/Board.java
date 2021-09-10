package com.edu.blog.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.ColumnDefault;
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
@Entity // Board 클래스가 MySQL 테이블에 생성된다.
public class Board {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	
	@Column(nullable = false, length = 100) 
	private String title;
	
	// 내용 
	// 대용량 데이터 
	@Lob
	private String content; 
	
	// 조회수 
	// 기본 값 정수형
	private int count;
	
	
	// Board : User = n : 1
	// 데이터베이스는 객체를 저장할 수 없으므로 저장할 때 필드 값을 지정해준다. 
	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name = "userId")
	private User user;	
	
	// 다중 값을 필드에 넣을 수 없기 때문에 데이터베이스에 추가하지 않는다.  
	// 외래키가 아니고 Reply에 있는 Board가 외래키이다. 
	// 조인을 통해 값을 얻기 위한 변수이다. 
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")
	private List<Reply> replies;
	
	@CreationTimestamp
	private LocalDateTime createDate;
	
}
