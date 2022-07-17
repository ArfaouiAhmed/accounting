INSERT INTO users (USERNAME, PASSWORD, ENABLED, FULLNAME) VALUES ('ahmed', '{noop}ahmed', true, 'Boha');
INSERT INTO users (USERNAME, PASSWORD, ENABLED, FULLNAME) VALUES ('hatem', '{noop}hatem', true, 'Jappa');
INSERT INTO users (USERNAME, PASSWORD, ENABLED, FULLNAME) VALUES ('test', '{noop}test', true, 'test user');

INSERT INTO roles (ID, USERNAME, ROLE) VALUES (0, 'ahmed', 'ROLE_MANAGER');
INSERT INTO roles (ID, USERNAME, ROLE) VALUES (1, 'hatem', 'ROLE_MANAGER');
INSERT INTO roles (ID, USERNAME, ROLE) VALUES (2, 'test', 'ROLE_USER');