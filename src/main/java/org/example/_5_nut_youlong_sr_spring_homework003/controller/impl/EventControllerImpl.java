package org.example._5_nut_youlong_sr_spring_homework003.controller.impl;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example._5_nut_youlong_sr_spring_homework003.controller.EventController;
import org.example._5_nut_youlong_sr_spring_homework003.model.entity.Event;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.EventRequest;
import org.example._5_nut_youlong_sr_spring_homework003.model.response.ApiResponse;
import org.example._5_nut_youlong_sr_spring_homework003.service.EventService;
import org.example._5_nut_youlong_sr_spring_homework003.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/events")
@RequiredArgsConstructor
public class EventControllerImpl implements EventController {

    private final EventService eventService;


    @GetMapping
    @Operation(summary = "Get All Events")
    @Override
    public ResponseEntity<ApiResponse<List<Event>>> getAllEvents(@RequestParam(defaultValue = "1") Integer page,
                                                                 @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok(ResponseUtil.success("Event Retrieved Successfully", eventService.getAllEvents(page, size), HttpStatus.OK ));
    }


    @GetMapping("/{event-id}")
    @Operation(summary = "Get Event by ID")
    @Override
    public ResponseEntity<ApiResponse<Event>> getEventById(@PathVariable("event-id") Integer id) {
        return ResponseEntity.ok(ResponseUtil.success("Event Retrieved Successfully", eventService.getEventById(id), HttpStatus.OK ));
    }



    @PostMapping
    @Operation(summary = "Add Event")
    @Override
    public ResponseEntity<ApiResponse<Event>> addEvent(@RequestBody EventRequest eventRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseUtil.success("Add Event Successfully",eventService.addEvent(eventRequest),HttpStatus.CREATED));
    }


    @DeleteMapping("/{event-id}")
    @Operation(summary = "Delete Event by ID")
    @Override
    public ResponseEntity<ApiResponse<Void>> deleteEventById(@PathVariable("event-id") Integer id) {
        eventService.deleteEventById(id);
        return ResponseEntity.ok(ResponseUtil.successNoPayload("Event Deleted Successfully", HttpStatus.OK ));
    }

    @PutMapping("/{event-id}")
    @Operation(summary = "Update Event by ID")
    @Override
    public ResponseEntity<ApiResponse<Event>> updateEventById(
            @PathVariable("event-id") Integer id,
            @RequestBody EventRequest eventRequest) {
        return ResponseEntity.ok(ResponseUtil.success("Event Updated Successfully", eventService.updateEventById(id, eventRequest), HttpStatus.OK));
    }
}
