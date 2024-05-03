CREATE TABLE IF NOT EXISTS device
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    serial_number BIGINT UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    is_calibrated BOOLEAN NOT NULL,
    user_id BIGINT,
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    statistic_frequency BIGINT,
    tariff FLOAT,
    regularity VARCHAR(255),
    telegram_chat_id BIGINT,
    telegram_display_name VARCHAR(255),
    telegram_icon VARCHAR(255),
    personal_account_region VARCHAR(255),
    personal_account_city VARCHAR(255),
    personal_account_street VARCHAR(255),
    personal_account_number VARCHAR(255),
    personal_account_phone_number VARCHAR(255),
    FOREIGN KEY fk_user_id (user_id) REFERENCES user (id),
    INDEX idx_user_id (user_id)
);
