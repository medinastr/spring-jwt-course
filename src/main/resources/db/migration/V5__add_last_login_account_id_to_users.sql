ALTER TABLE users
ADD COLUMN last_login_account_id BIGINT UNSIGNED;

ALTER TABLE users
ADD CONSTRAINT fk_last_login_account FOREIGN KEY (last_login_account_id) REFERENCES accounts(id);
