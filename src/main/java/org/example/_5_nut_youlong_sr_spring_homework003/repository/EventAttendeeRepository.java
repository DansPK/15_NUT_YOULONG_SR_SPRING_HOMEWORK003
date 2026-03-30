package org.example._5_nut_youlong_sr_spring_homework003.repository;

import org.apache.ibatis.annotations.*;
import org.example._5_nut_youlong_sr_spring_homework003.model.entity.Attendee;

import java.util.List;

@Mapper
public interface EventAttendeeRepository {

    @Results(id = "attendeeMapper", value = {
            @Result(property = Attendee.Fields.attendeeId,    column = "attendee_id"),
            @Result(property = Attendee.Fields.attendeeName,  column = "attendee_name"),
            @Result(property = Attendee.Fields.attendeeEmail, column = "email"),
    })
    @Select("""
       select a.* from attendees a
          join event_attendee ea on a.attendee_id = ea.attendee_id
          where ea.event_id = #{eventId}
    """)
    List<Attendee> getAttendeesByEventId(Integer eventId);

    @Insert("""
      insert into event_attendee values (#{attendeeId}, #{eventId})
    """)
    void save(Integer attendeeId, Integer eventId);


    @Delete("""
      delete from event_attendee
      where event_id = #{eventId}
    """)
    void deleteAllByEventId(Integer eventId);


}
