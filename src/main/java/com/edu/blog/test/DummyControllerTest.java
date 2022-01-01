package com.edu.blog.test;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.blog.model.RoleType;
import com.edu.blog.model.User;
import com.edu.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/dummy/join")
	public String join(User user) {

		System.out.println("회원가입 Post 요청!!!");
		user.setRole(RoleType.USER);
		userRepository.save(user);

		return "회원가입이 완료되었습니다.";
	}

	@GetMapping("/dummy/user/{idx}")
	public User selectUser(@PathVariable int idx) {


//		User user = userRepository.findById(idx).orElseThrow(new Supplier<IllegalArgumentException>() {
//
//			@Override
//			public IllegalArgumentException get() {
//				return new IllegalArgumentException("해당 회원이 존재하지 않습니다.");
//			}
//
//		});

		User user =  userRepository.findById(idx).orElseThrow(() -> {
			return new IllegalArgumentException("해당 회원이 존재하지 않습니다.");
		});

		return user;
	}

	@GetMapping("/dummy/user")
	public List<User> selectAllUser() {
		return userRepository.findAll();

	}
}
