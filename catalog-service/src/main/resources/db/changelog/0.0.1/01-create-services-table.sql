CREATE TABLE services
(
    id             UUID PRIMARY KEY,
    name           VARCHAR(100) NOT NULL,
    specialization VARCHAR(15)  NOT NULL CHECK (specialization IN ('Терапевт', 'Хирургия', 'Педиатрия',
                                                                   'Неврология', 'Стоматология', 'Гинекология',
                                                                   'Дерматология', 'Другое')),
    price          NUMERIC      NOT NULL,
    duration       INT          NOT NULL,
    description    TEXT
);