CREATE TABLE modules
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    course_id   BIGINT       NOT NULL REFERENCES courses (id) ON DELETE CASCADE,
    title       VARCHAR(255) NOT NULL,
    order_index INT          NOT NULL,

    UNIQUE (course_id, order_index)
);