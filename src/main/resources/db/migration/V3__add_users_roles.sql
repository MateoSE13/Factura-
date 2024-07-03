CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY NOT NULL,
    username VARCHAR (20) NOT NULL,
    password VARCHAR(200) NOT NULL,
    email VARCHAR(50) NOT NULL,
    locked BOOLEAN,
    disabled BOOLEAN
);

CREATE TABLE IF NOT EXISTS role(
    id SERIAL PRIMARY KEY NOT NULL,
    rol VARCHAR (25) NOT NULL,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);