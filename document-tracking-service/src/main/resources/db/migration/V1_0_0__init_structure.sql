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

CREATE OR REPLACE INDEX person_first_name_idx ON person(first_name);
CREATE OR REPLACE INDEX person_last_name_idx ON person(last_name);


-- BLOB's max size is 64KB
CREATE TABLE IF NOT EXISTS document (
  id               BIGINT NOT NULL AUTO_INCREMENT,
  name             VARCHAR(500) NOT NULL DEFAULT CONCAT('ANONYMOUS-', CAST(NOW() AS CHAR)),
  content          BLOB,
  file_length      BIGINT NOT NULL DEFAULT 0,
  word_count       BIGINT NOT NULL DEFAULT 0,
  checksum_sha_256 VARCHAR(500),
  created_by_id    BIGINT NOT NULL,
  created_by_email VARCHAR(500) NOT NULL DEFAULT 'UNKNOWN',
  created_at       TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (id),
  CONSTRAINT document_person_fk FOREIGN KEY(created_by_id) REFERENCES person(id)
);

CREATE OR REPLACE INDEX document_name_idx ON document(name);
