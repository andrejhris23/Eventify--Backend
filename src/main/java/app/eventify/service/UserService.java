package app.eventify.service;

import app.eventify.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User createUser(User newUser);

    User editUser(User editedUser);

    void deleteById(Long id);
}
