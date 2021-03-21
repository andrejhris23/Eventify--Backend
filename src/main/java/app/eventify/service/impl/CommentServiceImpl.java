package app.eventify.service.impl;

import app.eventify.model.Comment;
import app.eventify.repository.CommentRepository;

import java.util.List;

public class CommentServiceImpl {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public Comment editComment(Comment newComment) {
        Comment oldComment = commentRepository.findById(newComment.getId()).orElseThrow(RuntimeException::new);
        oldComment.setContent(newComment.getContent());
        return commentRepository.save(oldComment);
    }

    public Comment saveComment(Comment newComment) {
        return commentRepository.save(newComment);
    }

    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
