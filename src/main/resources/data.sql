-- Seed data
INSERT INTO venues (venue_name, location) VALUES
                                              ('Diamond Island Convention Center', 'Phnom Penh, Cambodia'),
                                              ('Raffles Hotel Le Royal',           'Phnom Penh, Cambodia'),
                                              ('Sokha Beach Resort',               'Sihanoukville, Cambodia'),
                                              ('Angkor Convention Centre',         'Siem Reap, Cambodia');

INSERT INTO events (event_name, event_date, venue_id) VALUES
                                                          ('Cambodia Tech Summit 2025',       '2025-06-15', 1),
                                                          ('Annual Gala Dinner',              '2025-07-20', 2),
                                                          ('Coastal Developer Conference',    '2025-08-10', 3),
                                                          ('Heritage & Culture Forum',        '2025-09-05', 4),
                                                          ('Startup Pitch Night',             '2025-09-25', 1);

INSERT INTO attendees (attendee_name, email) VALUES
                                                 ('Sokha Chea',    'sokha.chea@email.com'),
                                                 ('Dara Pich',     'dara.pich@email.com'),
                                                 ('Maly Heng',     'maly.heng@email.com'),
                                                 ('Visal Kong',    'visal.kong@email.com'),
                                                 ('Chantha Ros',   'chantha.ros@email.com'),
                                                 ('Piseth Lim',    'piseth.lim@email.com');

INSERT INTO event_attendee (attendee_id, event_id) VALUES
                                                       (1, 1), (1, 5),
                                                       (2, 1), (2, 2),
                                                       (3, 1), (3, 3),
                                                       (4, 2), (4, 4),
                                                       (5, 3), (5, 5),
                                                       (6, 4), (6, 5);