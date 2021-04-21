package app.eventify.web.controller;

import app.eventify.model.Comment;
import app.eventify.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> findAll(){
        return this.commentService.findAll();
    }

    @PostMapping("/{postId}/add")
    public Comment addNewComment(@PathVariable Long postId, @RequestParam String content, @RequestParam Long userId) {

        return commentService.createComment(content, postId, userId);
    }
}
