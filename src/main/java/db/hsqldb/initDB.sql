DROP TABLE events IF EXISTS;

CREATE TABLE events (
  id INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(30),
  location VARCHAR(30),
  eventdate DATE,
  description VARCHAR(30),
  sns  VARCHAR(30)
);


DROP TABLE auditlog IF EXISTS;

CREATE TABLE auditlog (
	id INTEGER IDENTITY PRIMARY KEY,
	objectid VARCHAR(20),
	userid VARCHAR(30),
	successind VARCHAR(10),
	auditdate DATE,
	auditmessage VARCHAR(50),
)

