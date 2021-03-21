package app.eventify.service;

import app.eventify.model.Event;

import java.util.List;

public interface EventService {

    List<Event> findAll();

    Event findById(Long id);

    void deleteById(Long id);
}
