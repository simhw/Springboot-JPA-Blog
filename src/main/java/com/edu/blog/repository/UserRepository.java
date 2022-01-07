package com.edu.blog.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.edu.blog.model.User;
import org.springframework.data.jpa.repository.Query;

// User 형의 Primary key 는 Integer 형이다.
public interface UserRepository extends JpaRepository<User, Integer>{
    // JPA Naming 빙식
    // SELECT * FROM WHERE id=?(1) AND password=?(2)
    User findByIdAndPassword(String id, String password);

    // NativeQuery 방식
    @Query(value =  "SELECT * FROM WHERE id=? AND password=?", nativeQuery = true)
    User login(String id, String password);
}
