CREATE TABLE userapp (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nom VARCHAR(100) NOT NULL,
                         email VARCHAR(150) UNIQUE NOT NULL,
                         password VARCHAR(255),
                         role VARCHAR(50) NOT NULL
);