CREATE TABLE roles
(
  id SERIAL PRIMARY KEY NOT NULL,
  name VARCHAR(255)
);

CREATE TABLE users
(
    id SERIAL NOT NULL PRIMARY KEY,
    password VARCHAR(255),
    username VARCHAR(255) UNIQUE
);

CREATE TABLE users_roles
(
  user_id INTEGER NOT NULL,
  role_id INTEGER NOT NULL,
  PRIMARY KEY (user_id, role_Id)
);