package app.eventify.service.impl;

import app.eventify.model.Event;
import app.eventify.model.User;
import app.eventify.model.exceptions.InvalidEventIdException;
import app.eventify.model.exceptions.InvalidUserIdException;
import app.eventify.repository.EventRepository;
import app.eventify.repository.UserRepository;
import app.eventify.service.EventService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
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
        Event event = eventRepository.findById(id).orElseThrow(() -> new InvalidEventIdException(id));
//        event.setGuests(new ArrayList<>());
//        eventRepository.save(event);
        eventRepository.deleteById(id);
    }

    @Override
    public Event createEvent(String name, String description, String image, int price, int capacity, Long userId) {
        /* User host = new User(); */ // get the logged user from spring security
        User host = this.userRepository.findById(userId).orElseThrow(() -> new InvalidUserIdException(userId));
        Event newEvent = new Event(name, description, image, price, capacity, host);

       // host.getCreatedEvents().add(newEvent);

//        host.setCreatedEvents();


        return eventRepository.save(newEvent);
    }

    public String joinEvent (Long eventId, Long userId){
        User guest = this.userRepository.findById(userId).orElseThrow (() -> new InvalidUserIdException(userId));
        Event event = this.eventRepository.findById(eventId).orElseThrow (() -> new InvalidEventIdException(eventId));
        String noCapacity = "Capacity Full";
        String joinedMessage = "You've joined";

        if (event.getCapacity() > 0){
           guest.getEnrolledEvents().add(event);
           userRepository.save(guest);
           //  Many to many
            return joinedMessage;
        }

        else return noCapacity;
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
