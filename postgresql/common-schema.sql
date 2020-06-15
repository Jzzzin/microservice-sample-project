CREATE SCHEMA eventuate;

DROP TABLE IF EXISTS eventuate.cdc_monitoring CASCADE;

CREATE TABLE eventuate.cdc_monitoring (
  reader_id VARCHAR(1000) PRIMARY KEY,
  last_time BIGINT
);

