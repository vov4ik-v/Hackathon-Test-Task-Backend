CREATE TABLE IF not exists user_role(
    user_id BIGINT  NOT NULL,
    roles INT default null,
    FOREIGN KEY fk_user_id (user_id) REFERENCES user(id),
    INDEX idx_user_id (user_id)
)