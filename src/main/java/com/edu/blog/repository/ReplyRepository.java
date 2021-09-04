package com.edu.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

}
