package org.example._5_nut_youlong_sr_spring_homework003.controller.impl;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.example._5_nut_youlong_sr_spring_homework003.controller.AttendeeController;
import org.example._5_nut_youlong_sr_spring_homework003.model.entity.Attendee;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.AttendeeRequest;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.AttendeeUpdateRequest;
import org.example._5_nut_youlong_sr_spring_homework003.model.response.ApiResponse;
import org.example._5_nut_youlong_sr_spring_homework003.service.AttendeeService;
import org.example._5_nut_youlong_sr_spring_homework003.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/attendees")
@RequiredArgsConstructor
public class AttendeeControllerImpl implements AttendeeController {

    private final AttendeeService attendeeService;

    @GetMapping
    @Operation(summary = "Get all attendees")
    @Override
    public ResponseEntity<ApiResponse<List<Attendee>>> getAllAttendees(
            @RequestParam(defaultValue = "1")  Integer page,
            @RequestParam(defaultValue = "10") Integer size) {

        return ResponseEntity.status(
                HttpStatus.OK
        ).body(ResponseUtil.success("Attendees retrieved successfully", attendeeService.getAllAttendees(page, size), HttpStatus.OK));
    }

    @GetMapping("/{attendee-id}")
    @Operation(summary = "Get attendee by id")
    @Override
    public ResponseEntity<ApiResponse<Attendee>> getAttendeeById(@PathVariable("attendee-id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(ResponseUtil.success("Attendee retrieved successfully", attendeeService.getAttendeeById(id), HttpStatus.OK));
    }

    @PostMapping
    @Operation(summary = "Add a new attendee")
    @Override
    public ResponseEntity<ApiResponse<Attendee>> addAttendee(@RequestBody AttendeeRequest attendeeRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseUtil.success("Attendee added successfully", attendeeService.addAttendee(attendeeRequest), HttpStatus.CREATED));
    }

    @DeleteMapping("/{attendee-id}")
    @Operation(summary = "Delete Attendee By ID")
    @Override
    public ResponseEntity<ApiResponse<Void>> deleteAttendeeById(@PathVariable("attendee-id") Integer id) {

        attendeeService.deleteAttendeeById(id);

        return ResponseEntity.ok(ResponseUtil.successNoPayload("Attendee Delete Successfully",HttpStatus.OK));
    }

    @PutMapping("/{attendee-id}")
    @Operation(summary = "Update attendee name by id")
    @Override
    public ResponseEntity<ApiResponse<Attendee>> updateAttendeeById(
            @PathVariable("attendee-id") @Positive(message = "attendeeId must be greater than 0") Integer id,
            @RequestBody @Valid AttendeeUpdateRequest attendeeUpdateRequest) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseUtil.success("Attendee updated successfully",
                        attendeeService.updateAttendeeById(id, attendeeUpdateRequest),
                        HttpStatus.OK));
    }
}
