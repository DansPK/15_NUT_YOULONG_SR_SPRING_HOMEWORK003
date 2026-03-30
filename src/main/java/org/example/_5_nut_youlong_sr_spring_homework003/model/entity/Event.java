package org.example._5_nut_youlong_sr_spring_homework003.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Event {
    private int eventId;
    private String eventName;
    private Date eventDate;
    private Venue venue;
    private List<Attendee> attendees;


}
