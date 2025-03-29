CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,

                       email VARCHAR(255) UNIQUE,
                       phone_number VARCHAR(20) UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       name VARCHAR(100),
                       role VARCHAR(50) DEFAULT 'USER',

                       google_id VARCHAR(100) UNIQUE,
                       facebook_id VARCHAR(100) UNIQUE,

                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
