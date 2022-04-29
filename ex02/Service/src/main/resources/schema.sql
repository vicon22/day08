DROP TABLE users IF EXISTS;

CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    email varchar(50) NOT NULL UNIQUE,
    password varchar(50) NOT NULL UNIQUE
)