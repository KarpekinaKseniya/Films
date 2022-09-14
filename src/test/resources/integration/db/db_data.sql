INSERT INTO director (id, first_name, last_name, birth_date) VALUES (1, 'Barys', 'Castro', '1963-01-14');
INSERT INTO director (id, first_name, last_name, birth_date) VALUES (2, 'Gavrel', 'Schuler', '1960-12-08');

INSERT INTO film (id, director_id, name, release_date, genre)
VALUES (2, 1, 'Foreigner Puzzle', '2001-03-15', 'Drama');
INSERT INTO film (id, director_id, name, release_date, genre)
VALUES (3, 1, 'Monument Without a Leader', '2001-03-15', 'Fantasy');
INSERT INTO film (id, director_id, name, release_date, genre)
VALUES (4, 2, 'Beasts and Ghosts', '1997-12-12', 'Horror');
INSERT INTO film (id, director_id, name, release_date, genre)
VALUES (5, 2, 'Dog of Tomorrow', '1998-01-16', 'Humor');
INSERT INTO film (id, director_id, name, release_date, genre)
VALUES (6, 2, 'Celebrate the West', '2003-06-10', 'Mystery');