CREATE TABLE test(
	id serial,
	first_name text NOT NULL,
	second_name text NOT NULL,
	birth_date date NOT NULL,
	CONSTRAINT pkey_director PRIMARY KEY (id)
)