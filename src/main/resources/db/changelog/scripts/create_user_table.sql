CREATE TABLE if not exists  user (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      username VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL,
                      password VARCHAR(3000) NOT NULL,
                      phone_number VARCHAR(255),
                      image_url VARCHAR(255) DEFAULT '"https://w7.pngwing.com/pngs/612/280/png-transparent-customer-user-userphoto-account-person-glyphs-icon.png"',
                      street VARCHAR(255),
                      city VARCHAR(255),
                      number INT,
                      region VARCHAR(255),
                      first_name VARCHAR(255),
                      last_name VARCHAR(255),
                      created_date TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
                      CONSTRAINT unique_username UNIQUE (username),
                      CONSTRAINT unique_email UNIQUE (email)
);
