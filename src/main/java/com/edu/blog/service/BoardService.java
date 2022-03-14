package com.edu.blog.service;

import com.edu.blog.dto.ReplyDto;
import com.edu.blog.model.Board;
import com.edu.blog.model.Reply;
import com.edu.blog.model.User;
import com.edu.blog.repository.BoardRepository;
import com.edu.blog.repository.ReplyRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public void 글쓰기 (Board board, User user) {
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
        return boardRepository.findById(id).orElseThrow(()->{
            return new IllegalArgumentException("삭재된 게시글입니다.");
        });
    }

    @Transactional
    public void 글삭제(int id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void 글수정(int id, Board req) {
        // 영속화
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            // Transaction 처리 (Rollback)
            return new IllegalArgumentException("삭재된 게시글입니다.");
        });
        board.setTitle(req.getTitle());
        board.setContent(req.getContent());
        // 헤당 함수 종료 시(Service 종료) Transaction 이 종료된다.
        // 이때 Dirty Checking 이 실행되고 자동으로 업데이트가 실행된다.
    }

    public void 댓글쓰기(ReplyDto replyDto, User user) {
        Board board = boardRepository.findById(replyDto.getBoardId()).orElseThrow(() -> {
            return new IllegalArgumentException("삭제된 게시글입니다.");
        });

        Reply reply = new Reply();
        reply.updateReply(board, replyDto.getContent(), user);

        replyRepository.save(reply);
    }

    public void 댓글삭제(int replyId) {
        replyRepository.deleteById(replyId);
    }
}
