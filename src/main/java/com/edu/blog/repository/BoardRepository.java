package com.edu.blog.repository;

import com.edu.blog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

// Board 형의 Primary key 는 Integer 형이다.
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
