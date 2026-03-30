package org.example._5_nut_youlong_sr_spring_homework003.service;

import org.example._5_nut_youlong_sr_spring_homework003.model.entity.Event;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.EventRequest;
import org.example._5_nut_youlong_sr_spring_homework003.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents(Integer page, Integer size);

    Event getEventById(Integer id);

    void deleteEventById(Integer id);

    Event addEvent(EventRequest eventRequest);

    Event updateEventById(Integer id, EventRequest eventRequest);
}
