CREATE TABLE IF not exists user
(
    id                   BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    email                VARCHAR(255)       NOT NULL UNIQUE,
    password             VARCHAR(3000) default null,
    phone_number         VARCHAR(255)  default null,
    image_url            VARCHAR(255)  default null,
    first_name           VARCHAR(255)  default null,
    last_name            VARCHAR(255)  default null,
    created_date         DATETIME(6)   default null
)