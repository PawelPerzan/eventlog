DROP TABLE events IF EXISTS;

CREATE TABLE events (
  id   INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(30),
  location VARCHAR(30),
  eventdate DATE,
  description VARCHAR(30),
  sns  VARCHAR(30)
);


