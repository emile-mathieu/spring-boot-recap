-- Drop existing tables if they exist
DROP TABLE IF EXISTS "users";
DROP TABLE IF EXISTS "books";

CREATE TABLE users (
id BIGINT DEFAULT nextval('users_id_seq') NOT NULL,
username VARCHAR(50) NOT NULL,
email VARCHAR(100) NOT NULL,
PRIMARY KEY (id)
);
CREATE TABLE books (
id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
title VARCHAR(255) NOT NULL,
author VARCHAR(100) NOT NULL,
user_id BIGINT REFERENCES users(id) ON DELETE CASCADE
);
