package com.edu.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.edu.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
}
