CREATE TABLE doctors
(
    id             UUID PRIMARY KEY,
    name           VARCHAR(15)        NOT NULL,
    surname        VARCHAR(15)        NOT NULL,
    patronymic     VARCHAR(15),
    specialization VARCHAR(15)        NOT NULL CHECK (specialization IN ('Терапевт', 'Хирургия', 'Педиатрия',
                                                                         'Неврология', 'Стоматология', 'Гинекология',
                                                                         'Дерматология',
                                                                         'Другое')),
    gender         VARCHAR(8)         NOT NULL CHECK (gender IN ('Мужской', 'Женский', 'Другой')),
    phone_number   VARCHAR(13) UNIQUE NOT NULL,
    experience     INTEGER,
    birth_date     DATE,
    image          VARCHAR(50) UNIQUE
);
