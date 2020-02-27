CREATE TABLE film (
	id serial,
	name text NOT NULL,
	genre text NOT NULL,
	director_id INTEGER,
	release_date date NOT NULL,
	PRIMARY KEY(id, director_id),
	FOREIGN KEY (director_id) REFERENCES director (id)
)