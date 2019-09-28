CREATE TABLE comment
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    parent_id BIGINT,
    type INT NOT NULL,
    creator INT NOT NULL,
    gmt_create BIGINT,
    gmt_modified BIGINT,
    like_count INT DEFAULT 0
);