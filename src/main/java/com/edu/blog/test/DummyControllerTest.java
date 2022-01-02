package com.edu.blog.test;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.edu.blog.model.RoleType;
import com.edu.blog.model.User;
import com.edu.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/dummy/user")
	public String insertUser(User user) {

		System.out.println("회원가입 Post 요청!!!");

		user.setRole(RoleType.USER);
		userRepository.save(user);

		return "회원가입이 완료되었습니다.";
	}

	@GetMapping("/dummy/user/{idx}")
	public User selectUser(@PathVariable int idx) {

		System.out.println("회원찾기 Get 요청!!!");

		User user = userRepository.findById(idx).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 회원이 존재하지 않습니다.");
			}

		});

//		User user =  userRepository.findById(idx).orElseThrow(() -> {
//			return new IllegalArgumentException("해당 회원이 존재하지 않습니다.");
//		});

		return user;
	}

	@GetMapping("/dummy/users")
	public List<User> selectAllUser() {

		return userRepository.findAll();
	}

	@GetMapping("/dummy/user")
	public List<User> selectPage(@PageableDefault(size = 2, sort = "idx", direction  = Sort.Direction.ASC) Pageable pageable) {

		Page<User> userPage = userRepository.findAll(pageable);
		List<User> users = userPage.getContent();

		return users;
	}

    @Transactional
	@PutMapping("/dummy/user/{idx}")
	public String updateUser(@PathVariable int idx, @RequestBody User req) {

		System.out.println("회원변경 Put 요청!!!");

		// 1. 기존 User 객체를 찾는다. (Persistence)
		User preUser =  userRepository.findById(idx).orElseThrow(() -> new IllegalArgumentException("해당 회원이 존재하지 않습니다."));

		// 2. 기존 User 객체의 값을 변경해준다.
		preUser.setEmail(req.getEmail());
		preUser.setPassword(req.getPassword());

		// user 객체의 idx 값을 전달해준다.
		// userRepository.save(preUser);

		// (1) Dirty Checking (2) Transactional 종료

		return "회원이 수정되었습니다.";
	}

	@DeleteMapping("/dummy/user/{idx}")
	public String deleteUser(@PathVariable int idx) {

		System.out.println("회원삭제 Delete 요청!!!");

		try {
			userRepository.deleteById(idx);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return "회원이 삭제되었습니다.";

	}                  
}
