CREATE TABLE IF NOT EXISTS team (
  id            BIGINT NOT NULL AUTO_INCREMENT,
  name          VARCHAR(1000) NOT NULL DEFAULT 'ANONYMOUS',
  created_at    TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (id),
  CONSTRAINT team_code_uq UNIQUE (name)
);

CREATE OR REPLACE INDEX team_name_idx ON team(name);


CREATE TABLE IF NOT EXISTS person (
  id            BIGINT NOT NULL AUTO_INCREMENT,
  team_id       BIGINT NOT NULL,
  email         VARCHAR(500) NOT NULL,
  first_name    VARCHAR(500) NOT NULL DEFAULT 'ANONYMOUS',
  last_name     VARCHAR(500) NOT NULL DEFAULT 'ANONYMOUS',
  created_at    TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (id),
  CONSTRAINT person_email_uq UNIQUE (email),
  CONSTRAINT person_team_fk FOREIGN KEY(team_id) REFERENCES team(id)
);

CREATE OR REPLACE INDEX person_created_at_idx ON person(created_at);


CREATE TABLE IF NOT EXISTS document (
  id               BIGINT NOT NULL AUTO_INCREMENT,
  name             VARCHAR(500) NOT NULL DEFAULT CONCAT('ANONYMOUS-', CAST(NOW() AS CHAR)),
  content          TEXT NOT NULL,
  file_length      BIGINT NOT NULL DEFAULT 0,
  created_at       TIMESTAMP NOT NULL DEFAULT NOW(),
  created_by_email VARCHAR(500) NOT NULL DEFAULT 'UNKNOWN',
  created_by_id    BIGINT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT document_person_fk FOREIGN KEY(created_by_id) REFERENCES person(id)
);

CREATE OR REPLACE INDEX document_name_idx ON document(name);
CREATE OR REPLACE INDEX document_created_at_idx ON document(created_at);