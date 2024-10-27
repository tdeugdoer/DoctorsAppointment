CREATE TABLE doctors
(
    id             UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name           VARCHAR(15)        NOT NULL,
    surname        VARCHAR(15)        NOT NULL,
    patronymic     VARCHAR(15),
    specialization VARCHAR(15)         NOT NULL CHECK (specialization IN ('THERAPY', 'SURGERY', 'PEDIATRICS',
                                                                          'NEUROLOGY', 'DENTISTRY', 'GYNECOLOGY',
                                                                          'DERMATOLOGICAL', 'OTHER')),
    gender         VARCHAR(5)         NOT NULL CHECK (gender IN ('Men', 'Women', 'Other')),
    phone_number   VARCHAR(13) UNIQUE NOT NULL,
    experience     INTEGER,
    birth_date     DATE
);
