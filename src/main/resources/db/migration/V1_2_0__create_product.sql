CREATE TABLE product (
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(127),
description TEXT
);

INSERT INTO product(name, description) VALUES ('car', 'a beautiful car');