package app.eventify.service;

import app.eventify.model.Event;

import java.util.List;

public interface EventService {

    List<Event> findAll();

    Event findById(Long id);

    void deleteById(Long id);

    Event createEvent(String name, String description, String image, int price, int capacity, Long userId);

    String joinEvent(Long eventId, Long userId);

    Event editEvent(Event editedEvent);
}
