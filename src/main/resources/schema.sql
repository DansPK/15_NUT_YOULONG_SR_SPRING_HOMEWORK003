-- Schema
CREATE TABLE venues (
                        venue_id  SERIAL PRIMARY KEY,
                        venue_name VARCHAR(100) NOT NULL,
                        location  VARCHAR(150)
);

CREATE TABLE events (
                        event_id   SERIAL PRIMARY KEY,
                        event_name VARCHAR(100) NOT NULL,
                        event_date DATE         NOT NULL,
                        venue_id   INT REFERENCES venues(venue_id) ON DELETE SET NULL
);

CREATE TABLE attendees (
                           attendee_id   SERIAL PRIMARY KEY,
                           attendee_name VARCHAR(100) NOT NULL,
                           email         VARCHAR(150) UNIQUE NOT NULL
);

CREATE TABLE event_attendee (
                                attendee_id INT REFERENCES attendees(attendee_id) ON DELETE CASCADE,
                                event_id    INT REFERENCES events(event_id)    ON DELETE CASCADE,
                                PRIMARY KEY (attendee_id, event_id)
);

