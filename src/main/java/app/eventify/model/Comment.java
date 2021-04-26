package app.eventify.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name="comments")

@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties ({"hibernateLazyInitializer", "handler", "user", "post"})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "full name is required")
    @Column(name="content")
    private String content;

    @Column(name="date")
    private LocalDate date;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Post post;

    public Comment(String content, User user, Post post) {
        this.content = content;
        this.user = user;
        this.post = post;
        this.date = LocalDate.now();
    }
}

