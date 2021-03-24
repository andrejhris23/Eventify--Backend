package app.eventify.service.impl;

import app.eventify.model.Event;
import app.eventify.model.User;
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
        return this.eventRepository.findById(id).orElseThrow(() -> new InvalidEventIdException(id));
    }

    @Override
    public void deleteById(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public Event createEvent(String name, String description, String image, int price, int capacity) {
        User host = new User(); // get the logged user from spring security
        Event newEvent = new Event(name, description, image, price, capacity, host);

        return eventRepository.save(newEvent);
    }

    @Override
    public Event editEvent(Event editedEvent) {
        Event event = eventRepository.findById(editedEvent.getId())
                .orElseThrow(() -> new InvalidEventIdException(editedEvent.getId()));

        event.setName(editedEvent.getName());
        event.setDescription(editedEvent.getDescription());
        event.setImage(editedEvent.getImage());
        event.setPrice(editedEvent.getPrice());
        event.setCapacity(editedEvent.getCapacity());
//        event.setGuests(editedEvent.getGuests());
//        event.setHost(editedEvent.getHost());

        return eventRepository.save(event);
    }
}
