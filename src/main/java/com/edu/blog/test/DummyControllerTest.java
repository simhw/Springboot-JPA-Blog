package com.edu.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.blog.model.RoleType;
import com.edu.blog.model.User;
import com.edu.blog.repository.UserRepository;

// HTML 파일이 아닌 데이터를 반환해주는 Controller는 RestController이다.
// JSON 형태로 객체 데이터를 반환
@RestController
public class DummyControllerTest {
	
	// DummyControllerTest가 메모리에 올라갈 때 UserRepository도 같이 올라간다. 
	// 의존성 주입 
	@Autowired
	private UserRepository userRepository;
	
	// id 주소로 파라미터를 전달받을 수 있다.
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		User user = userRepository.findById(id).orElseThrow(
				
				// Supplier는 인터페이스로 익명 함수로 작성되어야한다. 
				// This is a functional interface and can therefore be used as the assignment target for a lambda expression or method reference.
				
				new Supplier<IllegalArgumentException>() { 
					@Override
					public IllegalArgumentException get() {
						// Constructs an IllegalArgumentException with the specified detail message.
						return new IllegalArgumentException("사용자가 존재하지 않습니다.");
						}
					}
				);
		
		// 자바 객체를 반환하게 되면 MessageConverter가 자동 작동힌다. 
		// Jackson 라이브러리는 호출해서 user object를 JSON 형태로 변환해 브라우저에 반환한다. 
		return user;
	}
	
	
	// 한 페이지당 2건의 데이터를 출력 
	// http://localhost:8000/blog/dummy/user?page
	@GetMapping("dummy/user")
	public Page<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable){
		
		Page<User> page = userRepository.findAll(pageable);
		List<User> users = page.getContent();
		return page;
	}
	
	
	@GetMapping("/dummy/users")
	public List<User> list(){
		return userRepository.findAll();
	}
	
	// http://localhost:8000/blog/dummy/join
	@PostMapping("/dummy/join")
	public String join(User user) {
		
		//   insert into User(createdTime, email, password, role, username) 
		//   values(?, ?, ?, null, ?);

		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입";
	}
	
	@Transactional	// 함수 종료시 자동으로 commit된다. 
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, User reqUser) {	// json 데이터를 요청하면 java object로 전환 후 전달한다.
		
		// Persistence 
		// 데이터베이스에 있는 user를 context의 캐시 메모리에 저장한다. 
		// 요청받은 reqUser 객체의 데이터로 갱신할 경우 null 값으로 갱신되는 열이 있다. (id, username)
		User user = userRepository.findById(id).orElseThrow(()-> {
			return new IllegalArgumentException("사용자가 존재하지 않습니다.");
			}
		);
		user.setPassword(reqUser.getPassword());
		user.setEmail(reqUser.getEmail());
		//userRepository.save(user);
		return user;
	} 
	// commit
	// 영속화된 user 객체와 수정된 객체의 정보가 다르므로 자동으로 update문을 수행한다. 
	
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
		
		try {
			userRepository.deleteById(id);
		} catch (Exception ex) {
			// TODO: handle exception
			// EmptyResultDataAccessException
			return "사용자가 존재하지 않습니다."; 
		}

		return "회원삭제";
		
	}
}
