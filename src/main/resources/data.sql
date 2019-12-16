DROP TABLE IF EXISTS person;

CREATE TABLE person (
  id long AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  third_name VARCHAR(250) NOT NULL,
  birth_date TIMESTAMP  not NULL
);

