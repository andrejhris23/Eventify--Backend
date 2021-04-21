package app.eventify.controller;

import app.eventify.model.Event;
import app.eventify.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/")
    public List<Event> getAllEvents() {
        return eventService.findAll();
    }

    @PostMapping("/new")
    public Event createNewEvent(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String image,
            @RequestParam int price,
            @RequestParam int capacity,
            @RequestParam Long userId) {

        return eventService.createEvent(name, description, image, price, capacity, userId);
    }
}
