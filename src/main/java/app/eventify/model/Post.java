package app.eventify.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="posts")

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="content")
    private String content;

    @Column(name="date")
    private LocalDate date;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> likesFromUsers;

    @ManyToOne(fetch = FetchType.EAGER)
    private User userCreator;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
    private List<Comment> comments;

    public Post(String name, String content, User userCreator) {
        this.name = name;
        this.content = content;
        this.userCreator = userCreator;
        this.date = LocalDate.now();
        this.likesFromUsers = new ArrayList<>();
        this.comments = new ArrayList<>();
    }
}
