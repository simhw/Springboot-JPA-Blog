package com.edu.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.edu.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	// 시작 화면 이동
	@GetMapping({ "/", "" })
	public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		
		model.addAttribute("boards", boardService.글목록(pageable));
		return "index";
		// 해당 페이지로 모델의 정보를 들고간다.
	}

	// 글 쓰기 화면 이동
	@GetMapping("board/saveForm")
	public String saveForm() {
		
		return "board/saveForm";

	}

	// 글 상세보기 화면 이동
	@GetMapping("board/{id}")
	public String findById(@PathVariable int id, Model model) {
		
		model.addAttribute("board", boardService.글상세보기(id));
		return "/board/detail";
	
	}

	// 글 수정 화면 이동
	@GetMapping("board/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		
		model.addAttribute("board", boardService.글상세보기(id));
		return "/board/updateForm";
	
	}
}
