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

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()-> new InvalidUserIdException(id));
    }

    @Override
    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public User editUser(User editedUser){
        User oldUser = userRepository.findById(editedUser.getId())
                .orElseThrow(()-> new InvalidUserIdException(editedUser.getId()));

        // not sure if everything should be here
        oldUser.setDisplayName(editedUser.getDisplayName());
        oldUser.setFirstName(editedUser.getFirstName());
        oldUser.setLastName(editedUser.getLastName());
        oldUser.setEmail(editedUser.getEmail());
        oldUser.setImage(editedUser.getImage());
        oldUser.setCreatedEvents(editedUser.getCreatedEvents());
        oldUser.setEnrolledEvents(editedUser.getEnrolledEvents());
        oldUser.setEarnings(editedUser.getEarnings());
        oldUser.setRole(editedUser.getRole());
        oldUser.setCreatedPosts(editedUser.getCreatedPosts());
        oldUser.setLikedPosts(editedUser.getLikedPosts());

        return userRepository.save(oldUser);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
