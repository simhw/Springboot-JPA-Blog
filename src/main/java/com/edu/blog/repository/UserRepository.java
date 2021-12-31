package com.edu.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.edu.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUsername(String username);
	
//	//SELECT * FROM user WHERE username = ? AND password = ?;
//	User findByUsernameAndPassword(String username, String password);
	
//	@Query(value = "SELECT * FROM user WHERE username = ? AND password = ?", nativeQuery = true)
//	User login(String username, String password);
	
	
}
