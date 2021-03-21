package app.eventify.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")

@Data
@AllArgsConstructor
@NoArgsConstructor


public class User {

    @Column(name="name")
    private String name;

    @Id
    @Column(name="googleId")
    private int googleID;

    @Column(name="profileImage")
    private String profileImage;

    @OneToMany(mappedBy = "host")
    private List<Event> createdEvents;

    @ManyToMany
    private List<Event> enrolledEvents;

    @Column(name="earnings")
    private float earnings;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    private Role role;

    @OneToMany(mappedBy = "userCreator")
    private List<Post> createdPosts;

    @ManyToMany
    private List<Post> likedPosts;

}

