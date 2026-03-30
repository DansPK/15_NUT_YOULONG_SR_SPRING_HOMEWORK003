package org.example._5_nut_youlong_sr_spring_homework003.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class Venue
{
    private int venueId;
    private String venueName;
    private String location;
}
