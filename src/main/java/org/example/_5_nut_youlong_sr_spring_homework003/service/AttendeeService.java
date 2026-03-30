package org.example._5_nut_youlong_sr_spring_homework003.service;

import org.example._5_nut_youlong_sr_spring_homework003.model.entity.Attendee;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.AttendeeRequest;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.AttendeeUpdateRequest;

import java.util.List;

public interface AttendeeService {
    List<Attendee> getAllAttendees( Integer page, Integer size);


    Attendee getAttendeeById(Integer id);

    Attendee addAttendee(AttendeeRequest attendeeRequest);

    void deleteAttendeeById(Integer id);

    Attendee updateAttendeeById(Integer id, AttendeeUpdateRequest attendeeUpdateRequest);
}
