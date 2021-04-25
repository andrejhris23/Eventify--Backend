package app.eventify.web.controller;

import app.eventify.model.Event;
import app.eventify.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/getAllEvents")
    public List<Event> getAllEvents() {
        return eventService.findAll();
    }

    @GetMapping("/getEventById/{id}")
    public Event getEventById(@PathVariable("id") Long id) {
        return eventService.findById(id);
    }

    @PostMapping("/createEvent")
    public Event createNewEvent(
            @Valid
            @RequestBody String name,
            @RequestBody String description,
            @RequestBody String image,
            @RequestBody int price,
            @RequestBody int capacity,
            @RequestBody Long userId) {

        return eventService.createEvent(name, description, image, price, capacity, userId);
    }

    @PostMapping("/joinEvent")
    public String joinEvent(@Valid @RequestBody Long eventId,  Long userId ) {
        return eventService.joinEvent(eventId, userId);
    }

    @PatchMapping("/editEvent")
    public Event editEvent(@Valid @RequestBody Event editedEvent) {
        return eventService.editEvent(editedEvent);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long eventId) {
        eventService.deleteById(eventId);
    }
}
