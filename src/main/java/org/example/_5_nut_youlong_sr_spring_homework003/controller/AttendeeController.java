package org.example._5_nut_youlong_sr_spring_homework003.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.example._5_nut_youlong_sr_spring_homework003.model.entity.Attendee;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.AttendeeRequest;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.AttendeeUpdateRequest;
import org.example._5_nut_youlong_sr_spring_homework003.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AttendeeController {
    ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendees(
            @RequestParam(defaultValue = "1") @Positive(message = "page must be greater than 0") Integer page,
            @RequestParam(defaultValue = "10") @Positive(message = "size must be greater than 0") Integer size);

    ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@Positive(message = "attendeeId must be greater than 0") Integer attendeeId);

    ResponseEntity<ApiResponse<Attendee>> addAttendee(@Valid AttendeeRequest attendeeRequest);

    ResponseEntity<ApiResponse<Void>> deleteAttendeeById(@Positive(message = "attendeeId must be greater than 0") Integer id);

    ResponseEntity<ApiResponse<Attendee>> updateAttendeeById(@Positive(message = "attendeeId must be greater than 0") Integer id, @Valid AttendeeUpdateRequest attendeeUpdateRequest);



}
