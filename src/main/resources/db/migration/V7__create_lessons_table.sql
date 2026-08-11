CREATE TABLE lessons
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    module_id   BIGINT       NOT NULL REFERENCES modules (id) ON DELETE CASCADE,
    title       VARCHAR(255) NOT NULL,
    content     TEXT,
    order_index INT          NOT NULL,

    UNIQUE (module_id, order_index)
)