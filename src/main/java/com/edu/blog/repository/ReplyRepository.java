package com.edu.blog.repository;

import com.edu.blog.dto.ReplyDto;
import com.edu.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    @Modifying
    @Query(value = " insert into reply(board_id, content, user_id) values(?, ?, ?))", nativeQuery = true)
    int replySave(int boardId, String content, int userIdx);

}
