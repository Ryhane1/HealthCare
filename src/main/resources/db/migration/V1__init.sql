CREATE TABLE patient (
         id BIGINT AUTO_INCREMENT PRIMARY KEY,
         nom VARCHAR(100) NOT NULL,
         prenom VARCHAR(100) NOT NULL,
         email VARCHAR(150) UNIQUE NOT NULL,
         telephone VARCHAR(100),
         date_naissance DATE
);

CREATE TABLE medecin (
         id BIGINT AUTO_INCREMENT PRIMARY KEY,
         nom VARCHAR(100) NOT NULL,
         specialite VARCHAR(100) NOT NULL,
         email VARCHAR(150) UNIQUE NOT NULL,
         telephone VARCHAR(20)
);

CREATE TABLE rendez_vous (
         id BIGINT AUTO_INCREMENT PRIMARY KEY,
         date_rendez_vous DATETIME NOT NULL,
         statut VARCHAR(20) NOT NULL,
         patient_id BIGINT NOT NULL,
         medecin_id BIGINT NOT NULL,
         FOREIGN KEY (patient_id) REFERENCES patient(id),
         FOREIGN KEY (medecin_id) REFERENCES medecin(id)
);

CREATE TABLE dossier_medical (
         id BIGINT AUTO_INCREMENT PRIMARY KEY,
         diagnostic TEXT,
         observation TEXT,
         date_creation DATE NOT NULL,
         patient_id BIGINT NOT NULL UNIQUE,
         FOREIGN KEY (patient_id) REFERENCES patient(id)
);