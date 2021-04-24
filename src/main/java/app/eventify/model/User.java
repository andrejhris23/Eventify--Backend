package app.eventify.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")

@Data
@NoArgsConstructor


public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="oauthId")
    private String oauthId;

    @Column(name="email")
    private String email;

    @Column(name="displayName")
    private String displayName;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="image")
    private String image;

    @OneToMany(mappedBy = "host", fetch = FetchType.EAGER)
    private List<Event> createdEvents;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Event> enrolledEvents;

    @Column(name="earnings")
    private float earnings;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Comment> comments;

    private Role role;

    @OneToMany(mappedBy = "userCreator", fetch = FetchType.EAGER)
    private List<Post> createdPosts;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Post> likedPosts;

    public User(String oauthId, String email, String displayName, String firstName, String lastName, String image) {
        this.oauthId = oauthId;
        this.email = email;
        this.displayName = displayName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.image = image;
    }
}

