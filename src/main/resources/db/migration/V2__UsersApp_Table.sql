CREATE TABLE userApp (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         nom VARCHAR(100) NOT NULL,
                         email VARCHAR(150) UNIQUE NOT NULL,
                         password VARCHAR(100)
);