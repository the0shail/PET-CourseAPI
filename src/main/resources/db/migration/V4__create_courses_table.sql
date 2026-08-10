CREATE TABLE courses
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    author_id   BIGINT         NOT NULL REFERENCES users (id) ON DELETE RESTRICT,
    title       VARCHAR(255)   NOT NULL,
    description TEXT,
    price       NUMERIC(10, 2) NOT NULL DEFAULT 0 CHECK ( price >= 0 ),
    status      VARCHAR(20)    NOT NULL DEFAULT 'DRAFT' CHECK ( status IN ('DRAFT', 'PUBLISHED', 'ARCHIVED') ),
    created_at  timestamptz    NOT NULL DEFAULT now(),
    updated_at  timestamptz    NOT NULL DEFAULT now()
);

CREATE INDEX idx_courses_author_id ON courses (author_id);