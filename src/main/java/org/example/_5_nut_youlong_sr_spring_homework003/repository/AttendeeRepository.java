package org.example._5_nut_youlong_sr_spring_homework003.repository;


import org.apache.ibatis.annotations.*;
import org.example._5_nut_youlong_sr_spring_homework003.model.entity.Attendee;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.AttendeeRequest;
import org.example._5_nut_youlong_sr_spring_homework003.model.request.AttendeeUpdateRequest;

import java.util.List;

@Mapper
public interface AttendeeRepository {
    @Results(id="attendeeResultMap", value = {
            @Result(property = Attendee.Fields.attendeeId, column = "attendee_id"),
            @Result(property = Attendee.Fields.attendeeName, column = "attendee_name"),
            @Result(property = Attendee.Fields.attendeeEmail, column = "email")
    })
    @Select("""
SELECT * FROM attendees OFFSET #{offset} LIMIT #{size}
""")
    List<Attendee> getAllAttendees(Integer offset, Integer size);


    @Select("""
SELECT COALESCE(
    (SELECT true FROM attendees WHERE lower(email) = #{email} LIMIT 1),
    false
)
""")
    Boolean getAttendeeByEmail(String email);



    @ResultMap("attendeeResultMap")
    @Select("""
SELECT * FROM attendees WHERE attendee_id = #{id}
""")
    Attendee getAttendeeById(Integer id);


    @ResultMap("attendeeResultMap")
    @Select("""
INSERT INTO attendees (attendee_name, email)
VALUES (#{reg.attendeeName}, #{reg.attendeeEmail}) RETURNING *
""")
    Attendee addAttendee(@Param("reg") AttendeeRequest attendeeRequest);



    @Select("""
SELECT COALESCE(
    (SELECT true FROM event_attendee WHERE attendee_id = #{id} LIMIT 1),
    false
)
""")
    Boolean isAttendeeInEvents(Integer id);


    @Select("""
DELETE FROM attendees where attendee_id=#{id}
""")
    Void deleteAttendeeById(Integer id);


    @ResultMap("attendeeResultMap")
    @Select("""
UPDATE attendees
SET attendee_name = #{req.attendeeName}
WHERE attendee_id = #{id}
RETURNING *
""")
    Attendee updateAttendeeById(@Param("id") Integer id, @Param("req") AttendeeUpdateRequest attendeeUpdateRequest);
}
