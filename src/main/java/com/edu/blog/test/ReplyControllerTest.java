package com.edu.blog.test;

import com.edu.blog.model.Board;
import com.edu.blog.model.Reply;
import com.edu.blog.repository.BoardRepository;
import com.edu.blog.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReplyControllerTest {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    ReplyRepository replyRepository;

    @GetMapping("/test/board/{id}")
    public Board getBoard(@PathVariable int id){
        Board board = boardRepository.findById(id).get();
        return board;
    }

    @GetMapping("/test/replies")
    public List<Reply> getReplies(){
        List<Reply> replies = replyRepository.findAll();
        return replies;
    }
}
