CREATE TABLE user_profiles
(
    user_id    BIGINT PRIMARY KEY REFERENCES users (id) ON DELETE CASCADE,
    first_name VARCHAR(100),
    last_name  VARCHAR(100),
    bio        TEXT,
    avatar_url VARCHAR(500)
);