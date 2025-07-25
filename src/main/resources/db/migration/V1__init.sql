CREATE TABLE users (
    username VARCHAR(150) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities(
    username VARCHAR(150) NOT NULL,
    authority VARCHAR(50) NOT NULL,
     CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);

CREATE UNIQUE INDEX ix_auth_username
ON authorities (username, authority);