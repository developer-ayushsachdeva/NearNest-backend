package com.nearnest.backend.repository;

import com.nearnest.backend.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPingIdOrderByCreatedAtAsc(Long pingId);
}
