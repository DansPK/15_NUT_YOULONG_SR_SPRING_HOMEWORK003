package org.example._5_nut_youlong_sr_spring_homework003.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
public class Attendee {
    private int attendeeId;
    private String attendeeName;
    private String attendeeEmail;


}
