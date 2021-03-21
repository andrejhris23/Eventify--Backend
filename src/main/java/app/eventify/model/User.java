package app.eventify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name="password")
    @JsonIgnore
    private String password;

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

