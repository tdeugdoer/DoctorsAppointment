CREATE TABLE appointments
(
    id      UUID PRIMARY KEY,
    doctor  UUID        NOT NULL,
    patient UUID,
    status  VARCHAR(10) NOT NULL CHECK (status IN ('FREE', 'BOOKED', 'COMPLETED')),
    date    TIMESTAMP,
    price   NUMERIC
);
