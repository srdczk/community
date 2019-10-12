CREATE TABLE reply
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    question_id INT,
    type INT,
    creator VARCHAR(30),
    owner INT,
    gmt_create BIGINT
);