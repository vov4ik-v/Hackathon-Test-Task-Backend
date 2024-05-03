CREATE TABLE if not exists measurement
(
    id          SERIAL PRIMARY KEY,
    measurement BIGINT,
    is_submitted BOOLEAN,
    device_id   BIGINT NOT NULL,
    created_date TIMESTAMP,
    FOREIGN KEY (device_id) REFERENCES device (id)
);
