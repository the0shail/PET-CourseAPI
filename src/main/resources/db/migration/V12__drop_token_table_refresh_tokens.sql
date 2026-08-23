UPDATE refresh_tokens
SET token_hash = encode(sha256(convert_to(token, 'UTF8')), 'hex')
WHERE token_hash IS NULL;

ALTER TABLE refresh_tokens ALTER COLUMN token_hash SET NOT NULL;
ALTER TABLE refresh_tokens DROP COLUMN token;
