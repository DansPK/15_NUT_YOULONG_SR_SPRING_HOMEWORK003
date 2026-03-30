package org.example._5_nut_youlong_sr_spring_homework003.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.example._5_nut_youlong_sr_spring_homework003.model.entity.Attendee;
import org.example._5_nut_youlong_sr_spring_homework003.model.entity.Venue;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.AttendeeRequest;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.AttendeeUpdateRequest;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.VenueRequest;
import org.example._5_nut_youlong_sr_spring_homework003.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface VenueController {

    ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(
            @RequestParam(defaultValue = "1") @Positive(message = "page must be greater than 0") Integer page,
            @RequestParam(defaultValue = "10") @Positive(message = "size must be greater than 0") Integer size);

    ResponseEntity<ApiResponse<Venue>> getVenueById(@Positive(message = "venueId must be greater than 0") Integer id);

    ResponseEntity<ApiResponse<Venue>> addVenue(@Valid VenueRequest  venueRequest);

    ResponseEntity<ApiResponse<Void>> deleteVenueById(@Positive(message = "venueId must be greater than 0") Integer id);

    ResponseEntity<ApiResponse<Venue>> updateVenueById(@Positive(message = "venueId must be greater than 0") Integer id, @Valid VenueRequest  venueRequest);



}
