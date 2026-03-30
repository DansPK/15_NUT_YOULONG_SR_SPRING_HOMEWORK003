package org.example._5_nut_youlong_sr_spring_homework003.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.example._5_nut_youlong_sr_spring_homework003.model.entity.Event;
import org.example._5_nut_youlong_sr_spring_homework003.model.entity.Venue;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.EventRequest;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.VenueRequest;
import org.example._5_nut_youlong_sr_spring_homework003.model.response.ApiResponse;
import org.modelmapper.internal.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface EventController {

    ResponseEntity<ApiResponse<List<Event>>> getAllEvents( @RequestParam(defaultValue = "1") @Positive(message = "page must be greater than 0") Integer page,
                                                          @RequestParam(defaultValue = "10") @Positive(message = "size must be greater than 0") Integer size);


    ResponseEntity<ApiResponse<Event>> getEventById(@Positive(message = "venueId must be greater than 0") Integer id);

    ResponseEntity<ApiResponse<Event>> addEvent(@Valid EventRequest eventRequest);

    ResponseEntity<ApiResponse<Void>> deleteEventById(@Positive(message = "venueId must be greater than 0") Integer id);

    ResponseEntity<ApiResponse<Event>> updateEventById(@Positive(message = "venueId must be greater than 0") Integer id, @Valid  EventRequest eventRequest);

}
