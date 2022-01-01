package com.edu.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.edu.blog.model.User;

// User 형의 Primary key 는 Integer 형이다.
public interface UserRepository extends JpaRepository<User, Integer>{
}
