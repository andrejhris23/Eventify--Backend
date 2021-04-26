package app.eventify.web.controller;

import app.eventify.model.Comment;
import app.eventify.service.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/getAllComments")
    public List<Comment> findAll(){
        return this.commentService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Comment findById(@PathVariable("id") Long commentId) {
        return commentService.findById(commentId);
    }

    @PostMapping("/createComment")
    public Comment addNewComment(@Valid @RequestParam String content, @RequestParam Long postId, @RequestParam Long userId) {
        return commentService.createComment(content, postId, userId);
    }

    @PostMapping("/editComment")
    public Comment editComment(@Valid @RequestBody Comment editedComment) {
        return commentService.editComment(editedComment);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        commentService.deleteById(id);
    }
}


