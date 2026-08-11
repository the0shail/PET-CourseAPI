CREATE TABLE users
(
    id         BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email      VARCHAR(255) NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    role       VARCHAR(20)  NOT NULL CHECK ( role IN ('STUDENT', 'INSTRUCTOR', 'ADMIN') ),
    created_at timestamptz  NOT NULL DEFAULT now()
);