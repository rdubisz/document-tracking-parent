CREATE TABLE IF NOT EXISTS task_definition (
  id            BIGINT NOT NULL AUTO_INCREMENT,
  name          VARCHAR(1000) NOT NULL DEFAULT 'ANONYMOUS',
  code          VARCHAR(255) NOT NULL DEFAULT (uuid()),
  PRIMARY KEY (id),
  CONSTRAINT task_definition_uq UNIQUE (code)
);

CREATE TABLE IF NOT EXISTS task_operation (
  id                 BIGINT NOT NULL AUTO_INCREMENT,
  task_definition_id BIGINT NOT NULL,
  duration           BIGINT NOT NULL DEFAULT 0,
  start_time         TIMESTAMP NOT NULL DEFAULT NOW(),
  PRIMARY KEY (id),
  CONSTRAINT task_operation_task_definition_fk FOREIGN KEY(task_definition_id) REFERENCES task_definition(id)
);

CREATE OR REPLACE INDEX task_operation_start_time_idx ON task_operation(start_time);
