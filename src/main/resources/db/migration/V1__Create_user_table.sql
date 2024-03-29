CREATE TABLE user
(
  id INT AUTO_INCREMENT PRIMARY KEY,
  account_id VARCHAR(100),
  name VARCHAR(50),
  token CHAR(50),
  gmt_create BIGINT,
  gmt_modified BIGINT,
  UNIQUE (account_id)
);
