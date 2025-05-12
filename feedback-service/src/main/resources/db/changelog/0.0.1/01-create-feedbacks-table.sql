CREATE TABLE feedbacks
(
    id            UUID PRIMARY KEY,
    appointment   VARCHAR(255) NOT NULL,
    service       UUID         NOT NULL,
    doctor        UUID         NOT NULL,
    patient       UUID         NOT NULL,
    rating        INTEGER      NOT NULL CHECK (rating >= 1 AND rating <= 5),
    creation_time TIMESTAMP    NOT NULL,
    comment       VARCHAR(255)
);