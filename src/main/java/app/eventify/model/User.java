package app.eventify.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor


public class User {


    private String name;
    @Id
    private int googleID;

    private String profileImage;

    private List<Event> createdEvents;

    private List<Event> enrolledEvents;

    private int earnings;

    private List<Comment> comments;

    private Role role;

    private List<Post> createdPosts;

    private List<Post> likedPosts;





    public User(String name, int googleID) {
        this.name = name;
        this.googleID = googleID;
    }


}

