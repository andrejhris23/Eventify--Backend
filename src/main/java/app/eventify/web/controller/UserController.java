package app.eventify.web.controller;


import app.eventify.model.User;
import app.eventify.service.UserService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping ("/getUserById/:userId")
        public User findById(@PathVariable Long userId){
            return userService.findById(userId);
        }

        @PostMapping ("/createNewUser")
        public User createUser (@RequestBody User user) {
            return userService.createUser(user);

        }
    }



   /*

   List<User> findAll();

    User findById(Long id);

    User createUser(User newUser);

    User editUser(User editedUser);

    void deleteById(Long id);

    */