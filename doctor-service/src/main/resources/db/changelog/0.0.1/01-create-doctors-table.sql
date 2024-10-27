CREATE TABLE doctors
(
    id             UUID PRIMARY KEY,
    name           VARCHAR(15)        NOT NULL,
    surname        VARCHAR(15)        NOT NULL,
    patronymic     VARCHAR(15),
    specialization VARCHAR(15)        NOT NULL CHECK (specialization IN ('Therapy', 'Surgery', 'Pediatrics',
                                                                         'Neurology', 'Dentistry', 'Gynecology',
                                                                         'Dermatological', 'Other')),
    gender         VARCHAR(5)         NOT NULL CHECK (gender IN ('Men', 'Women', 'Other')),
    phone_number   VARCHAR(13) UNIQUE NOT NULL,
    experience     INTEGER,
    birth_date     DATE
);
