CREATE TABLE cars
(
    id                   BIGINT AUTO_INCREMENT NOT NULL,
    brand                VARCHAR(255)          NULL,
    type                 VARCHAR(255)          NULL,
    age                  INT                   NULL,
    car_condition        VARCHAR(255)          NULL,
    last_kilometer_state INT                   NULL,
    car_seller_id        BIGINT                NULL,
    CONSTRAINT pk_cars PRIMARY KEY (id)
);
