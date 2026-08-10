CREATE TABLE course_categories
(
    category_id BIGINT NOT NULL REFERENCES categories (id) ON DELETE CASCADE,
    course_id   BIGINT NOT NULL REFERENCES courses (id) ON DELETE CASCADE,

    PRIMARY KEY (category_id, course_id)
);

CREATE INDEX idx_course_categories_category_id ON course_categories (category_id);