package app.eventify.service.impl;

import app.eventify.model.*;
import app.eventify.model.exceptions.InvalidUserIdException;
import app.eventify.repository.CommentRepository;
import app.eventify.repository.EventRepository;
import app.eventify.repository.PostRepository;
import app.eventify.repository.UserRepository;
import app.eventify.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PostRepository postRepository;

    private final EventRepository eventRepository;

    private final CommentRepository commentRepository;

    public UserServiceImpl(UserRepository userRepository, PostRepository postRepository, EventRepository eventRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.eventRepository = eventRepository;
        this.commentRepository = commentRepository;
    }


    public User findById(Long id) {
        return this.userRepository.findById(id).orElseThrow(()-> new InvalidUserIdException(id));

    }

    public User create(String name, String profileImage, List<Event> createdEvents, float earnings, List<Comment> comments, Role role, List<Post> createdPosts, List<Post> likedPosts) {
//        List<Event> newEventsList = this.eventRepository.findAllById();

        return null;
    }
}
