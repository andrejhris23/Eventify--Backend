package app.eventify.service;

import app.eventify.model.Event;
import app.eventify.model.User;

import java.util.List;

public interface EventService {

    List<Event> findAll();

    Event findById(Long id);

    void deleteById(Long id);

    Event createEvent(String name, String description, String image, int price, int capacity);

    Event editEvent(Event editedEvent);
}
