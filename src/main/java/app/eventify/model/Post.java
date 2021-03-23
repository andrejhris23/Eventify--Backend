package app.eventify.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;

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

    @ManyToMany
    private List<User> likesFromUsers;

    @ManyToOne
    private User userCreator;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;
}
