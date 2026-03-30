package org.example._5_nut_youlong_sr_spring_homework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.example._5_nut_youlong_sr_spring_homework003.exception.ConflictException;
import org.example._5_nut_youlong_sr_spring_homework003.exception.NotFoundException;
import org.example._5_nut_youlong_sr_spring_homework003.model.entity.Venue;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.VenueRequest;
import org.example._5_nut_youlong_sr_spring_homework003.repository.VenueRepository;
import org.example._5_nut_youlong_sr_spring_homework003.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {
    private final VenueRepository venueRepository;


    @Override
    public List<Venue> getAllVenues(Integer page, Integer size) {

        Integer offset = (page - 1) * size;

        return venueRepository.getAllVenues(offset, size);
    }

    @Override
    public Venue getVenueById(Integer id) {

        Venue venue = venueRepository.getVenueById(id);
        if (venue == null) {
            throw new NotFoundException("Venue not found with the given ID");
        }
        return venue;
    }

    @Override
    public Venue addVenue(VenueRequest venueRequest) {

        Boolean isVenueNameExisted = venueRepository.getVenueByName(venueRequest.getVenueName());
        if (isVenueNameExisted) {
            throw new ConflictException(
                    "Venue Name already exists",
                    "http://localhost:8080/errors/duplicate-Venue"
            );
        }
        return venueRepository.addVenue(venueRequest);
    }

    @Override
    public void deleteVenueById(Integer id) {

        if (venueRepository.getVenueById(id) == null) {
            throw new NotFoundException("Venue not found with the given ID");
        }

        if (venueRepository.isVenueInEvents(id)) {
            throw new ConflictException(
                    "This Venue is still on some events. Remove from those events first.",
                    "http://localhost:8080/errors/operation-not-allowed"
            );
        }

        venueRepository.deleteVenueById(id);

    }

    @Override
    public Venue updateVenueById(Integer id, VenueRequest venueRequest) {


        if (venueRepository.getVenueById(id) == null) {
            throw new NotFoundException("Venue not found with the given ID");
        }

        Boolean isVenueNameExisted = venueRepository.getVenueByName(venueRequest.getVenueName());
        if (isVenueNameExisted) {
            throw new ConflictException(
                    "Venue Name already exists",
                    "http://localhost:8080/errors/duplicate-Venue"
            );
        }

        return venueRepository.updateVenueById(id, venueRequest);
    }
}

