CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY NOT NULL UNIQUE AUTO_INCREMENT,
    username VARCHAR,
    login VARCHAR,
    password VARCHAR,
    token VARCHAR
);
INSERT into users VALUES (123, "admin1", "first", "lol", "token1");
INSERT into users VALUES (1234, "admin2", "second", "lol", "token2");
INSERT into users VALUES (12345, "admin3", "third", "lol", "token3");
INSERT into users VALUES (123456, "admin4", "four", "lol", "token4");
