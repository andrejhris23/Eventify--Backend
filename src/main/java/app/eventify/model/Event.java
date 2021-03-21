package app.eventify.model;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "events")

@Data
@NoArgsConstructor

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="imageUrl")
    private String image;

    @Column(name="price")
    private float price;

    @Column(name="capacity")
    private int capacity;

    @ManyToMany
    private List<User> guests;

    @ManyToOne
    private User host;


    public Event(String name, String description, String image, int price, int capacity, List<User> guests, User host) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.capacity = capacity;
        this.guests = new ArrayList<>();
        this.host = host;
    }
}
