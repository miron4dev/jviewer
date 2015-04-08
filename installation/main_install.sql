CREATE TABLE IF NOT EXISTS 'properties' (
  'invitationID' INTEGER DEFAULT NULL
);

INSERT INTO properties ('invitationID') VALUES ('1030011');

CREATE TABLE IF NOT EXISTS 'rooms' (
  id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  name TEXT NOT NULL UNIQUE,
  password TEXT DEFAULT NULL
);

INSERT INTO rooms ('id', 'name', 'password') VALUES (1, 'Main', NULL);

CREATE TABLE IF NOT EXISTS users (
  id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE NOT NULL,
  name TEXT NOT NULL UNIQUE,
  password TEXT NOT NULL,
  role TEXT NOT NULL DEFAULT 'user',
  faculty TEXT NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT 1
);

INSERT INTO users ('id', 'name', 'password', 'role', 'faculty', 'enabled') VALUES
  (1, 'Evgeny Mironenko', 'qwerty', 'ROLE_USER', 'Faculty of Information Technology', '1');
INSERT INTO users ('id', 'name', 'password', 'role', 'faculty', 'enabled') VALUES
  (2, 'Admin Admin', 'admin1234+', 'ROLE_ADMIN', 'Faculty of Information Technology', '1');