package org.example._5_nut_youlong_sr_spring_homework003.repository;

import org.apache.ibatis.annotations.*;
import org.example._5_nut_youlong_sr_spring_homework003.model.entity.Event;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.EventRequest;

import java.util.List;

@Mapper
public interface EventRepository {
    @Results(id = "eventMapper", value = {
            @Result(property = Event.Fields.eventId, column = "event_id"),
            @Result(property = Event.Fields.eventName, column = "event_name"),
            @Result(property = Event.Fields.eventDate, column = "event_Date"),
            @Result(property = Event.Fields.venue, column = "venue_id",
                    one = @One(select = "org.example._5_nut_youlong_sr_spring_homework003.repository.VenueRepository.getVenueById")),
            @Result(property = Event.Fields.attendees, column = "event_id",
                    many = @Many(select = "org.example._5_nut_youlong_sr_spring_homework003.repository.EventAttendeeRepository.getAttendeesByEventId"))
    })
    @Select("""
select event_id, event_name, event_date, venue_id from events limit #{size} offset #{offset}
""")
    List<Event> getAllEvents(Integer offset, Integer size);




    @Select("""
SELECT COALESCE(
    (SELECT true FROM events WHERE event_name = #{Event} LIMIT 1),
    false
)
""")
    Boolean getEventByName( String Event);



    @ResultMap("eventMapper")
    @Select("""
select event_id, event_name, event_date, venue_id from events where event_id = #{id}
""")
    Event getEventById(Integer id);



    @Select("""
DELETE from events where event_id=#{id}
""")
    void deleteEventById(Integer id);



    @Select("""
INSERT INTO events (event_name, event_date, venue_id)
VALUES (#{req.eventName}, #{req.eventDate}, #{req.venueId})
RETURNING event_id
""")
    Integer addEvent(@Param("req") EventRequest eventRequest);

    @Select("""
UPDATE events
SET event_name = #{req.eventName},
    event_date = #{req.eventDate},
    venue_id   = #{req.venueId}
WHERE event_id = #{id}
""")
    void updateEventById( Integer id, @Param("req") EventRequest eventRequest);
}
