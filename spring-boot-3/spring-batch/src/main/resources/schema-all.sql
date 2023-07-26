DROP TABLE IF EXISTS coffee;

CREATE TABLE coffee  (
    coffee_id BIGINT AUTO_INCREMENT,
    brand VARCHAR(20),
    origin VARCHAR(20),
    characteristics VARCHAR(30),
    PRIMARY KEY (coffee_id)
);