package app.eventify.model;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String eventId;

    private String name;

    private String description;

    private String image;

    private int price;

    private int capacity;

    private List<User> users;

    private User host;


    public Event(String name, String description, String image, int price, int capacity, List<User> users, User host) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.capacity = capacity;
        this.users = users;
        this.host = host;
    }
}
