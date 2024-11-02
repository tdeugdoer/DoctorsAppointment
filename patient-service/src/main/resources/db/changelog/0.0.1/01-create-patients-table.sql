CREATE TABLE patients
(
    id           UUID PRIMARY KEY,
    name         VARCHAR(15)        NOT NULL,
    surname      VARCHAR(15)        NOT NULL,
    patronymic   VARCHAR(15),
    gender       VARCHAR(5)         NOT NULL CHECK (gender IN ('Men', 'Women', 'Other')),
    phone_number VARCHAR(13) UNIQUE NOT NULL,
    birth_date   DATE,
    image        VARCHAR(50) UNIQUE
);
