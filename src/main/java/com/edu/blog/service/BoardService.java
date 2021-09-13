package com.edu.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.blog.config.auth.PrincipalDetail;
import com.edu.blog.dto.ReplyRequestDto;
import com.edu.blog.model.Board;
import com.edu.blog.model.Reply;
import com.edu.blog.model.User;
import com.edu.blog.repository.BoardRepository;
import com.edu.blog.repository.ReplyRepository;
import com.edu.blog.repository.UserRepository;

// 트랜잭션을 관리해준다.
// 다수의 기능들(CRUD)을 하나의 서비스로 처리하기 위해 사용된다. 

// 스프링이 스캔을 통해 Bean에 등록해준다 
// Inversion of Control(IoC)

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public void 글쓰기(Board board, User user) { // title, content, user

		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}

	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable) {
		return boardRepository.findAll(pageable);

	}

	@Transactional(readOnly = true)
	public Board 글상세보기(int id) {
		return boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("아이디를 찾을 수 없습니다.");
		});
	}

	@Transactional
	public void 글삭제(int id, PrincipalDetail principal) {
		
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("해당 글이 삭제되었습니다.");
			});
		
		if(board.getUser().getId() != principal.getUser().getId()) {
			throw new IllegalStateException("잘못된 접근입니다.");
		}
		boardRepository.deleteById(id);
	}
	
	
	@Transactional
	public void 글수정(int id, Board requestBoard, PrincipalDetail principal) {
		
		// Persistence Context 
		Board board = boardRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("게시글을 찾을 수 없습니다.");
		});
		
		if(board.getUser().getId() != principal.getUser().getId()) {
			throw new IllegalStateException("잘못된 접근입니다.");
		}
		
		board.setTitle(requestBoard.getTitle());
		board.setContent(requestBoard.getContent());
		
		// service 함수 종료 시 transaction이 종료된다. 
		// Spring Dirty Checking이 변화를 자동으로 감지하고 자동으로 commit된다.  
	
	}
	

	@Transactional
	public void 댓글쓰기(User user, int boardId, Reply requestReply) {

		// 영속화 
		Board board = boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("게시글을 찾을 수 없습니다.");
		});
		
		requestReply.setUser(user);
		requestReply.setBoard(board);
		
		replyRepository.save(requestReply);
		
		
	}

	@Transactional
	public void 댓글쓰기(ReplyRequestDto replyRequestDto) {
		
		// 영속화 
		User user = userRepository.findById(replyRequestDto.getUserId()).orElseThrow(() -> {
			return new IllegalArgumentException("게시글을 찾을 수 없습니다.");
		});
		
		// 영속화  
		Board board = boardRepository.findById(replyRequestDto.getBoardId()).orElseThrow(() -> {
			return new IllegalArgumentException("게시글을 찾을 수 없습니다.");
		});
		
		Reply reply = new Reply();
		reply.updateReply(replyRequestDto.getContent(), user, board);
		
		replyRepository.save(reply);
		
	}
}
