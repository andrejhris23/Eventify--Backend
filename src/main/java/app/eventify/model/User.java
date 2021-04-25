package app.eventify.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name="users")

@Data
@NoArgsConstructor


public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "oauthId is required")
    @Column(name="oauthId", unique = true)
    private String oauthId;

    @NotNull(message = "email is required")
    @Email(message = "must be a correct format of an email")
    @Column(name="email", unique = true)
    private String email;

    @NotNull(message = "full name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "full name must be a string")
    @Column(name="displayName")
    private String displayName;

    @NotNull(message = "first name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "first name must be a string")
    @Column(name="firstName")
    private String firstName;

    @NotNull(message = "last name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "last name must be a string")
    @Column(name="lastName")
    private String lastName;

    @NotNull(message = "image is required")
    @URL(message = "image must be an URL")
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

