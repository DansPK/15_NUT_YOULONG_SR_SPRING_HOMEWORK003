package org.example._5_nut_youlong_sr_spring_homework003.controller.impl;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example._5_nut_youlong_sr_spring_homework003.controller.VenueController;
import org.example._5_nut_youlong_sr_spring_homework003.model.entity.Venue;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.VenueRequest;
import org.example._5_nut_youlong_sr_spring_homework003.model.response.ApiResponse;
import org.example._5_nut_youlong_sr_spring_homework003.service.VenueService;
import org.example._5_nut_youlong_sr_spring_homework003.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/venues")
@RequiredArgsConstructor
public class VenueControllerImpl implements VenueController {

    private final VenueService venueService;

    @GetMapping
    @Operation(summary = "Get all Venues" )
    @Override
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenues(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok(ResponseUtil.success("Venues Retrived Successfully", venueService.getAllVenues(page, size), HttpStatus.OK));
    }



    @GetMapping("/{venue-id}")
    @Operation(summary = "Get Venue By Id")
    @Override
    public ResponseEntity<ApiResponse<Venue>> getVenueById(@PathVariable("venue-id") Integer id) {
        return ResponseEntity.ok(ResponseUtil.success("Venue Retrived Successfully", venueService.getVenueById(id), HttpStatus.OK));
    }


    @PostMapping
    @Operation(summary = "Add Venue")
    @Override
    public ResponseEntity<ApiResponse<Venue>> addVenue(@RequestBody VenueRequest venueRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseUtil.success("Venue Add successfully", venueService.addVenue(venueRequest),HttpStatus.CREATED
        ));
    }


    @DeleteMapping
    @Operation(summary = "Delete Venue By Id")
    @Override
    public ResponseEntity<ApiResponse<Void>> deleteVenueById(Integer id) {
        venueService.deleteVenueById(id);
        return ResponseEntity.ok(ResponseUtil.successNoPayload("Deleted venue Successfully", HttpStatus.OK));
    }



    @PutMapping("/{venue-id}")
    @Operation(summary = "Update Venue By Id ")
    @Override
    public ResponseEntity<ApiResponse<Venue>> updateVenueById(@PathVariable("venue-id") Integer id, @RequestBody VenueRequest venueRequest) {
        return ResponseEntity.ok(ResponseUtil.success("Update Venue Successfully", venueService.updateVenueById(id , venueRequest), HttpStatus.OK));
    }
}
