package com.nearnest.backend.service;

import com.nearnest.backend.entity.Comment;
import com.nearnest.backend.entity.Ping;
import com.nearnest.backend.repository.CommentRepository;
import com.nearnest.backend.repository.PingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PingRepository pingRepository;

    public CommentService(
            CommentRepository commentRepository,
            PingRepository pingRepository
    ) {
        this.commentRepository = commentRepository;
        this.pingRepository = pingRepository;
    }

    public Comment addComment(Comment comment) {
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        Ping ping = pingRepository.findById(comment.getPingId())
                .orElseThrow(() -> new RuntimeException("Ping not found"));

        ping.setCommentsCount(ping.getCommentsCount() + 1);

        pingRepository.save(ping);

        return savedComment;
    }

    public List<Comment> getCommentsByPingId(Long pingId) {
        return commentRepository.findByPingIdOrderByCreatedAtAsc(pingId);
    }
}