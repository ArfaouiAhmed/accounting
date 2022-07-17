DROP TABLE roles IF EXISTS CASCADE;
DROP TABLE users IF EXISTS CASCADE;

CREATE TABLE users(
    username VARCHAR(20)          NOT NULL,
    password VARCHAR(20)          NOT NULL,
    enabled  BOOLEAN DEFAULT TRUE NOT NULL,
    fullname VARCHAR(256)         NOT NULL,
    PRIMARY KEY (username)
);

CREATE TABLE roles(
    id       INTEGER IDENTITY PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    role     VARCHAR(20) NOT NULL
);
ALTER TABLE roles ADD CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username);
CREATE INDEX fk_username_idx ON roles (username);