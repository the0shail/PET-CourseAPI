CREATE TABLE enrollments
(
    id               BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    student_id       BIGINT      NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    course_id        BIGINT      NOT NULL REFERENCES courses (id) ON DELETE RESTRICT,
    status           VARCHAR(20) NOT NULL DEFAULT 'ACTIVE' CHECK ( status IN ('ACTIVE', 'COMPLETED', 'CANCELLED') ),
    progress_percent INT         NOT NULL DEFAULT 0 CHECK ( progress_percent BETWEEN 0 AND 100),
    enrolled_at      timestamptz NOT NULL DEFAULT now(),

    UNIQUE (student_id, course_id)
);

CREATE INDEX idx_enrollments_course_id ON enrollments (course_id);