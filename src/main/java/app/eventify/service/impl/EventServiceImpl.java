package app.eventify.service.impl;

import app.eventify.model.Event;
import app.eventify.model.exceptions.InvalidEventIdException;
import app.eventify.repository.EventRepository;
import app.eventify.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    @Override
    public List<Event> findAll() {
        return eventRepository.findAll();
    }
    @Override
    public Event findById(Long id) {
        return this.eventRepository.findById(id).orElseThrow(()-> new InvalidEventIdException(id));
    }

    /* falat ushte servisi */

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event createEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event editEvent(Event editedEvent) {
        Event event = eventRepository.findById(editedEvent.getId())
                .orElseThrow(() -> new InvalidEventIdException(editedEvent.getId()));

        event.setDescription(editedEvent.getDescription());
        // za site

        return eventRepository.save(event);
    }
}
