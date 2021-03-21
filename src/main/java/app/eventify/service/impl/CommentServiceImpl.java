package app.eventify.service.impl;

import app.eventify.model.Comment;
import app.eventify.model.exceptions.InvalidCommentIdException;
import app.eventify.repository.CommentRepository;
import app.eventify.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }
    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(RuntimeException::new);
    }
    @Override
    public Comment editComment(Comment newComment) {
        Comment oldComment = commentRepository.findById(newComment.getId()).orElseThrow(InvalidCommentIdException::new);
        oldComment.setContent(newComment.getContent());
        return commentRepository.save(oldComment);
    }
    @Override
    public Comment saveComment(Comment newComment) {
        return commentRepository.save(newComment);
    }
    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
