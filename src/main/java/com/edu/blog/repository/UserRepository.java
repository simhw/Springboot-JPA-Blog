package com.edu.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

import com.edu.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
//	JpaRepository에 기본적인 CRUD 기능이 정의되어있다. 
//	Data Access Object와 동일한 기능을 한다. 
//	자동으로 Bean 등록이 된다. 
//	@Repository 생략 가능하다.


	Optional<User> findByUsername(String username);
	
//	//SELECT * FROM user WHERE username = ? AND password = ?;
//	User findByUsernameAndPassword(String username, String password);
	
//	@Query(value = "SELECT * FROM user WHERE username = ? AND password = ?", nativeQuery = true)
//	User login(String username, String password);
	
	
}
