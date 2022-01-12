package com.edu.blog.service;

import com.edu.blog.model.Board;
import com.edu.blog.model.User;
import com.edu.blog.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void 글쓰기 (Board board, User user) {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

}
