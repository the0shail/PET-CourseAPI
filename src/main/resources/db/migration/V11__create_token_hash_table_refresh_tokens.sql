ALTER TABLE refresh_tokens ADD COLUMN token_hash VARCHAR(64);
CREATE UNIQUE INDEX ux_refresh_token_hash ON refresh_tokens (token_hash);
