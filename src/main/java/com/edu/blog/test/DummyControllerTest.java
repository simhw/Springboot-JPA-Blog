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
}
