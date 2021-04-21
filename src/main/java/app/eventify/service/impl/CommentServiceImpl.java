package app.eventify.service.impl;

import app.eventify.model.Comment;
import app.eventify.model.Post;
import app.eventify.model.User;
import app.eventify.model.exceptions.InvalidCommentIdException;
import app.eventify.model.exceptions.InvalidPostIdException;
import app.eventify.model.exceptions.InvalidUserIdException;
import app.eventify.repository.CommentRepository;
import app.eventify.repository.PostRepository;
import app.eventify.repository.UserRepository;
import app.eventify.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new InvalidCommentIdException(id));
    }

    @Override
    public Comment editComment(Comment newComment) {
        Comment oldComment = commentRepository.findById(newComment.getId())
                .orElseThrow(() -> new InvalidCommentIdException(newComment.getId()));

        oldComment.setContent(newComment.getContent());
        oldComment.setDate(newComment.getDate());

        return commentRepository.save(oldComment);
    }

   /* @Override
    public Comment createComment(String content, Long postId) {
        return null;
    }*/

    @Override
    public Comment createComment(String content, Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new InvalidPostIdException(postId));
        User currentUser = this.userRepository.findById(userId).orElseThrow(() -> new InvalidUserIdException(userId));
       /* User currentUser = new User(); */    // get this from spring security
        Comment newComment = new Comment(content, currentUser, post);

        return commentRepository.save(newComment);
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
