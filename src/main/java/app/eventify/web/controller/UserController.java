package app.eventify.web.controller;


import app.eventify.model.User;
import app.eventify.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public  List<User> findAll(){
        return userService.findAll();
    }

    @GetMapping ("/getUserById/{id}")
    public User findById(@PathVariable("id") Long userId){
        return userService.findById(userId);
    }

    @PostMapping ("/createNewUser")
    public User createUser (@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @PatchMapping("/editUser")
    public User editUser(@Valid @RequestBody User newUser) {
        return userService.editUser(newUser);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }
    }
