CREATE TABLE IF NOT EXISTS director
(
    id         serial PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    birth_date DATE         NOT NULL
);

CREATE TABLE IF NOT EXISTS film
(
    id           serial PRIMARY KEY,
    director_id  INT NOT NULL,
    name         VARCHAR(255),
    release_date DATE,
    genre        VARCHAR(255),

    FOREIGN KEY (director_id) REFERENCES director (id)
);