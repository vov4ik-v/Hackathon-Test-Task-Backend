CREATE TABLE if not exists user_role (
                           user_id BIGINT REFERENCES user(id),
                           roles VARCHAR(255),
                           PRIMARY KEY (user_id, roles)
);