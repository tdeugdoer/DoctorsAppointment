CREATE TABLE appointments
(
    id      UUID PRIMARY KEY,
    service UUID        NOT NULL,
    doctor  UUID        NOT NULL,
    patient UUID,
    status  VARCHAR(12) NOT NULL CHECK (status IN
                                        ('FREE', 'BOOKED', 'CHECKED_IN', 'IN_PROGRESS', 'COMPLETED', 'NO_SHOW')),
    date    TIMESTAMP,
    price   NUMERIC
);
