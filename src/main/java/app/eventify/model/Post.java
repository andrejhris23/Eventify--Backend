package app.eventify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="posts")

@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties({"userCreator"})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "post name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "post name must be a string")
    @Column(name="name")
    private String name;

    @NotNull(message = "post content is required")
    @Column(name="content")
    private String content;

    @Column(name="date")
    private LocalDate date;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> likesFromUsers;
    @JsonIgnore
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

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", likesFromUsers=" + likesFromUsers +
                ", userCreator=" + userCreator +
                ", comments=" + comments +
                '}';
    }
}
