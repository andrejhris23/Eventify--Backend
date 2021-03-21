package app.eventify.model;

import lombok.Data;


import javax.persistence.Entity;
import java.util.List;

@Data

public class Event {

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
