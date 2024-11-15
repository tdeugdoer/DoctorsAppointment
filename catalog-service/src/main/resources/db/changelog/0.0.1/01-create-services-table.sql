CREATE TABLE services
(
    id             UUID PRIMARY KEY,
    name           VARCHAR(100) NOT NULL,
    specialization VARCHAR(15)  NOT NULL CHECK (specialization IN ('Therapy', 'Surgery', 'Pediatrics',
                                                                   'Neurology', 'Dentistry', 'Gynecology',
                                                                   'Dermatological', 'Other')),
    price          NUMERIC      NOT NULL,
    description    TEXT
);