CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);


INSERT INTO users (name) VALUES ('Alice'), ('Bob');
