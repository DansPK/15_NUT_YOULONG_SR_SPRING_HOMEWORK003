package org.example._5_nut_youlong_sr_spring_homework003.repository;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.ibatis.annotations.*;
import org.example._5_nut_youlong_sr_spring_homework003.model.entity.Attendee;
import org.example._5_nut_youlong_sr_spring_homework003.model.entity.Venue;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.VenueRequest;

import java.util.List;

@Mapper
public interface VenueRepository {

    @Results(id = "venueMapper" ,value= {
            @Result(property = Venue.Fields.venueId, column = "venue_id"),
            @Result(property = Venue.Fields.venueName, column = "venue_name"),
            @Result(property = Venue.Fields.location, column = "location")
    })
    @Select("""
        SELECT * from venues OFFSET #{offset} LIMIT #{size}
""")
    List<Venue> getAllVenues(Integer offset, Integer size);



    @ResultMap("venueMapper")
    @Select("""
        SELECT * from venues where venue_id=#{id}
""")
    Venue getVenueById(Integer id);


    @Select("""
SELECT COALESCE(
    (SELECT true FROM venues WHERE venue_name = #{venueName} LIMIT 1),
    false
)
""")
    Boolean getVenueByName( String venueName);



    @Select("""
SELECT COALESCE(
    (SELECT true FROM events WHERE venue_id = #{id} LIMIT 1),
    false
)
""")
    Boolean isVenueInEvents(Integer id);



    @Select("""
DELETE FROM venues where venue_id = #{id}
""")
    Void deleteVenueById(Integer id);




    @ResultMap("venueMapper")
    @Select("""
INSERT INTO venues (venue_name, location)
VALUES (#{req.venueName}, #{req.location}) RETURNING *
""")
    Venue addVenue(@Param("req") VenueRequest venueRequest);




    @ResultMap("venueMapper")
    @Select("""
UPDATE venues
SET venue_name = #{req.venueName}, location = #{req.location}
WHERE venue_id = #{id}
RETURNING *
""")
    Venue updateVenueById(Integer id, @Param("req") VenueRequest venueRequest);

}
