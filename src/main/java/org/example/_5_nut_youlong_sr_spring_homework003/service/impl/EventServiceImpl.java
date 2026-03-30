package org.example._5_nut_youlong_sr_spring_homework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.example._5_nut_youlong_sr_spring_homework003.exception.ConflictException;
import org.example._5_nut_youlong_sr_spring_homework003.exception.NotFoundException;
import org.example._5_nut_youlong_sr_spring_homework003.model.entity.Event;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.EventRequest;
import org.example._5_nut_youlong_sr_spring_homework003.repository.AttendeeRepository;
import org.example._5_nut_youlong_sr_spring_homework003.repository.EventAttendeeRepository;
import org.example._5_nut_youlong_sr_spring_homework003.repository.EventRepository;
import org.example._5_nut_youlong_sr_spring_homework003.repository.VenueRepository;
import org.example._5_nut_youlong_sr_spring_homework003.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventAttendeeRepository eventAttendeeRepository;
    private final VenueRepository venueRepository;
    private final AttendeeRepository attendeeRepository;

    @Override
    public List<Event> getAllEvents(Integer page, Integer size) {
        Integer offset = (page - 1) * size;
        return eventRepository.getAllEvents(offset, size);
    }

    @Override
    public Event getEventById(Integer id) {
        Event event = eventRepository.getEventById(id);

        if (event == null) {
            throw new NotFoundException("Event not found with the given ID");
        }

        return event;
    }

    @Override
    public void deleteEventById(Integer id) {

        if (eventRepository.getEventById(id) == null) {
            throw new NotFoundException("Event not found with the given ID");
        }
        eventRepository.deleteEventById(id);

    }

    @Override
    public Event addEvent(EventRequest eventRequest) {

        //Check duplicate event name
        if (eventRepository.getEventByName(eventRequest.getEventName())) {
            throw new ConflictException(
                    "Event Name already exists",
                    "http://localhost:8080/errors/duplicate-event"
            );
        }

        //Check venue exists
        if (venueRepository.getVenueById(eventRequest.getVenueId()) == null) {
            throw new NotFoundException("Venue not found with Given ID");
        }

        // Check every attendee exists
        for (Integer attendeeId : eventRequest.getAttendeeId()) {
            if (attendeeRepository.getAttendeeById(attendeeId) == null) {
                throw new NotFoundException("Attendee not found with Given ID");
            }
        }

        // Insert event and get new event_id
        Integer eventId = eventRepository.addEvent(eventRequest);

        //Save each attendee into event_attendee table
        for (Integer attendeeId : eventRequest.getAttendeeId()) {
            eventAttendeeRepository.save(attendeeId, eventId);
        }

        //  Return full event
        return eventRepository.getEventById(eventId);
    }

    @Override
    public Event updateEventById(Integer id, EventRequest eventRequest) {
        //Check event exists
        if (eventRepository.getEventById(id) == null) {
            throw new NotFoundException("Event not found with the given ID");
        }

        // Check venue exists
        if (venueRepository.getVenueById(eventRequest.getVenueId()) == null) {
            throw new NotFoundException("Venue not found with the given ID");
        }

        // Check every attendee exists
        for (Integer attendeeId : eventRequest.getAttendeeId()) {
            if (attendeeRepository.getAttendeeById(attendeeId) == null) {
                throw new NotFoundException("Attendee not found with the given ID");
            }
        }

        // Update event fields
        eventRepository.updateEventById(id, eventRequest);

        // Replace attendees
        eventAttendeeRepository.deleteAllByEventId(id);
        for (Integer attendeeId : eventRequest.getAttendeeId()) {
            eventAttendeeRepository.save(attendeeId, id);
        }

        // Return full updated event
        return eventRepository.getEventById(id);
    }
}
