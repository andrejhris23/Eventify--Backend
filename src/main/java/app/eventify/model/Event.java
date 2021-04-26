package app.eventify.model;

import com.sun.istack.Nullable;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
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

    @NotNull(message = "event name is required")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "event name must be a string")
    @Column(name="name")
    private String name;

    @NotNull(message = "description is required")
    @Column(name="description")
    private String description;

    @Column(name="imageUrl")
    private String image;

    @Column(name="price")
    private float price;

    @Positive(message = "the capacity must be a positive number")
    @NotNull(message = "event capacity is required")
    @Column(name="capacity")
    private int capacity;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany(mappedBy = "enrolledEvents", fetch = FetchType.EAGER)
    private List<User> guests;

    @Nullable
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User host;



    public Event(String name, String description, String image, int price, int capacity, User host) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.capacity = capacity;
        this.guests = new ArrayList<>();
        this.host = host;

        this.guests = new ArrayList<>();


    }
}
