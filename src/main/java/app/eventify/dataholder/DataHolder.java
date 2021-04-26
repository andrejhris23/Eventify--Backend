package app.eventify.dataholder;

import app.eventify.model.Comment;
import app.eventify.model.Event;
import app.eventify.model.Post;
import app.eventify.model.User;
import app.eventify.repository.CommentRepository;
import app.eventify.repository.EventRepository;
import app.eventify.repository.PostRepository;
import app.eventify.repository.UserRepository;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Component
public class DataHolder {

    private final CommentRepository commentRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    List<Event> initialEvents;
    List<User> initialUsers;
    List<Comment> initialComments;
    List<Post> initialPosts;



    public DataHolder(CommentRepository commentRepository, EventRepository eventRepository, UserRepository userRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
        this.postRepository = postRepository;

        initialEvents = new ArrayList<>();
        initialComments =  new ArrayList<>();
        initialUsers =  new ArrayList<>();
        initialPosts = new ArrayList<>();

        generateData();
        fillDBAtStart();
    }

    public void generateData() {
         User testuser1 = new User("abcd", "testuser@gmail.com", "Testuser", "TestUserName", "TestUserovichLastName", "https://gravatar.com/avatar/1f82b0492a0a938288c2d5b70534a1fb?s=400&d=robohash&r=x" );
         User testuser2 = new User("qwerty", "qwerty@gmail.com", "qwertuser", "FirstQwerty", "QwertyovichLast", "https://www.nretnil.com/avatar/LawrenceEzekielAmos.png" );

        initialUsers.add(testuser1);
       initialUsers.add(testuser2);

      //  Event event1 = new Event("EventOne", "The first description for this majestic event", "https://i.pinimg.com/originals/12/54/09/1254093eda28964ac2ea4d158e7c0706.png", 35, 100, "abcd");
    }

   /* this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.capacity = capacity;
        this.guests = new ArrayList<>();
        this.host = host; */

    public void fillDBAtStart() {

        if (userRepository.count() == 0) {
            userRepository.saveAll(initialUsers);
            commentRepository.saveAll(initialComments);
            postRepository.saveAll(initialPosts);
            eventRepository.saveAll(initialEvents);
        }
        else {
            System.out.println("Bazata e vekje puna");
        }
    }
}



