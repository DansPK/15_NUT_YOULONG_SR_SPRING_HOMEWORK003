package org.example._5_nut_youlong_sr_spring_homework003.service;

import org.example._5_nut_youlong_sr_spring_homework003.model.entity.Venue;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.VenueRequest;
import org.example._5_nut_youlong_sr_spring_homework003.model.response.ApiResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VenueService {


    List<Venue> getAllVenues(Integer page, Integer size);

    Venue getVenueById(Integer id);

    Venue addVenue(VenueRequest venueRequest);

    void deleteVenueById(Integer id);

    Venue updateVenueById(Integer id, VenueRequest venueRequest);
}

