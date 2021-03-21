package app.eventify.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

import java.util.List;
@Data
@AllArgsConstructor

public class Post {

    private String name;

    private LocalDate date;

    private List<User> likesFromUsers;

    private User userOwner;
}
