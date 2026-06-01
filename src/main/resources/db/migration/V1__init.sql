-- Migration V1: Créer table userapp (Single Table Inheritance)
CREATE TABLE userapp (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password VARCHAR(255),
    role VARCHAR(50) NOT NULL,
    telephone VARCHAR(20),
    user_type VARCHAR(50) DEFAULT 'UTILISATEUR',
    specialite VARCHAR(100) NULL,
    prenom VARCHAR(100) NULL,
    date_naissance DATE NULL
);

-- Table rendez_vous (référence userapp pour patient_id et medecin_id)
CREATE TABLE rendez_vous (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date_rendez_vous DATETIME NOT NULL,
    statut VARCHAR(20) NOT NULL,
    patient_id BIGINT NOT NULL,
    medecin_id BIGINT NOT NULL,
    FOREIGN KEY (patient_id) REFERENCES userapp(id),
    FOREIGN KEY (medecin_id) REFERENCES userapp(id)
);

-- Table dossier_medical (référence userapp pour patient_id)
CREATE TABLE dossier_medical (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    diagnostic TEXT,
    observation TEXT,
    date_creation DATE NOT NULL,
    patient_id BIGINT NOT NULL UNIQUE,
    FOREIGN KEY (patient_id) REFERENCES userapp(id)
);

