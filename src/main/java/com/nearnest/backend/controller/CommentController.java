package com.nearnest.backend.controller;

import com.nearnest.backend.entity.Comment;
import com.nearnest.backend.service.CommentService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pings")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{pingId}/comments")
    public Comment addComment(
            @PathVariable Long pingId,
            @RequestBody Comment comment,
            Authentication authentication
    ) {

        Long userId = (Long) authentication.getCredentials();

        comment.setPingId(pingId);
        comment.setUserId(userId);

        return commentService.addComment(comment);
    }

    @GetMapping("/{pingId}/comments")
    public List<Comment> getComments(@PathVariable Long pingId) {
        return commentService.getCommentsByPingId(pingId);
    }
}