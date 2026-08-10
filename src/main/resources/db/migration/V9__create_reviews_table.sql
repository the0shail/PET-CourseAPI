CREATE TABLE reviews
(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    author_id BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    course_id BIGINT NOT NULL REFERENCES course (id) ON DELETE CASCADE,
    rating INT NOT NULL CHECK ( rating BETWEEN 1 AND 5),
    body TEXT,
    created_at timestamptz NOT NULL DEFAULT now(),

    UNIQUE (author_id, course_id)
);

CREATE INDEX idx_reviews_course_id ON reviews (course_id);