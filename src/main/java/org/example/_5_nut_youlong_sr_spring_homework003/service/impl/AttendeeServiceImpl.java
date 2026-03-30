package org.example._5_nut_youlong_sr_spring_homework003.service.impl;

import lombok.RequiredArgsConstructor;
import org.example._5_nut_youlong_sr_spring_homework003.exception.ConflictException;
import org.example._5_nut_youlong_sr_spring_homework003.exception.NotFoundException;
import org.example._5_nut_youlong_sr_spring_homework003.model.entity.Attendee;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.AttendeeRequest;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.AttendeeUpdateRequest;
import org.example._5_nut_youlong_sr_spring_homework003.repository.AttendeeRepository;
import org.example._5_nut_youlong_sr_spring_homework003.service.AttendeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {
    private final AttendeeRepository attendeeRepository;
    @Override
    public List<Attendee> getAllAttendees(Integer page, Integer size) {
        Integer offset = (page - 1) * size;
        return attendeeRepository.getAllAttendees(offset, size);
    }

    @Override
    public Attendee getAttendeeById(Integer id) {

        Attendee attendee = attendeeRepository.getAttendeeById(id);
        if (attendee == null) {
            throw new NotFoundException("Attendee Not Found with the given ID");
        }
        return attendee;
    }

    @Override
    public Attendee addAttendee(AttendeeRequest attendeeRequest) {
        Boolean isEmailExisted = attendeeRepository.getAttendeeByEmail(attendeeRequest.getAttendeeEmail().toLowerCase());
        if (isEmailExisted) {
            throw new ConflictException(
                    "Attendee email already exists",
                    "http://localhost:8080/errors/duplicate-user"
            );
        }
        return attendeeRepository.addAttendee(attendeeRequest);
    }



    @Override
    public void deleteAttendeeById(Integer id) {
        if (attendeeRepository.getAttendeeById(id) == null) {
            throw new NotFoundException("Attendee not found with the given ID");
        }

        if (attendeeRepository.isAttendeeInEvents(id)) {
            throw new ConflictException(
                    "This attendee is still on some events. Remove from those events first.",
                    "http://localhost:8080/errors/operation-not-allowed"
            );
        }
        attendeeRepository.deleteAttendeeById(id);



    }

    @Override
    public Attendee updateAttendeeById(Integer id, AttendeeUpdateRequest attendeeUpdateRequest) {

        Attendee attendee = attendeeRepository.getAttendeeById(id);


        if (attendee == null) {
            throw new NotFoundException("Attendee not found with the given ID");
        }
        return attendeeRepository.updateAttendeeById(id, attendeeUpdateRequest);
    }
}
