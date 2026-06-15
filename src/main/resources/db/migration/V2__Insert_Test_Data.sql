-- Migration V3: Insérer données de test (Single Table Inheritance)

-- Insérer les utilisateurs (admin et utilisateurs standard)
INSERT INTO userapp (nom, email, password, role, user_type, telephone) VALUES
('admin', 'admin@healthcare.com', '$2a$10$SlVZQcXvLx7RBL2gd9K.pOqfV9tpuWIlnI8YBWqN.OQD4q5G8Ksfa', 'ADMIN', 'UTILISATEUR', NULL); -- password: admin123

-- Insérer les médecins (user_type='MEDECIN')
INSERT INTO userapp (nom, email, password, role, user_type, specialite, telephone) VALUES
('Dr. Ahmed Hassan', 'dr.ahmed@clinic.com', '$2a$10$Rv.8ZsKI.BdxJmJJ3c.ZeOJ8gfVJVIbI8cJbF3OkW0aPHV9iJHzba', 'MEDECIN', 'MEDECIN', 'Cardiologie', '+212612345678'),
('Dr. Fatima Benali', 'dr.fatima@clinic.com', '$2a$10$Rv.8ZsKI.BdxJmJJ3c.ZeOJ8gfVJVIbI8cJbF3OkW0aPHV9iJHzba', 'MEDECIN', 'MEDECIN', 'Dermatologie', '+212612345679'),
('Dr. Mohamed El Khatib', 'dr.elkhattib@clinic.com', '$2a$10$Rv.8ZsKI.BdxJmJJ3c.ZeOJ8gfVJVIbI8cJbF3OkW0aPHV9iJHzba', 'MEDECIN', 'MEDECIN', 'Orthopedie', '+212612345680'),
('Dr. Leila Morocco', 'dr.leila@clinic.com', '$2a$10$Rv.8ZsKI.BdxJmJJ3c.ZeOJ8gfVJVIbI8cJbF3OkW0aPHV9iJHzba', 'MEDECIN', 'MEDECIN', 'Neurologie', '+212612345681'),
('Dr. Karim Saadi', 'dr.karim@clinic.com', '$2a$10$Rv.8ZsKI.BdxJmJJ3c.ZeOJ8gfVJVIbI8cJbF3OkW0aPHV9iJHzba', 'MEDECIN', 'MEDECIN', 'Radiologie', '+212612345682');

-- Insérer les patients (user_type='PATIENT')
INSERT INTO userapp (nom, email, password, role, user_type, prenom, date_naissance, telephone) VALUES
('patient1', 'patient1@healthcare.com', '$2a$10$h1/bM5EYpQK3bR8sL2nP1.KJx8EkM0W9YpV6tZ3Q4X1C5D9m7Hk4m', 'PATIENT', 'PATIENT', 'Jean', '1985-05-15', '+212611111111'),
('Dupont', 'jean.dupont@email.com', '$2a$10$h1/bM5EYpQK3bR8sL2nP1.KJx8EkM0W9YpV6tZ3Q4X1C5D9m7Hk4m', 'PATIENT', 'PATIENT', 'Jean', '1985-05-15', '+212611111111'),
('Martin', 'marie.martin@email.com', '$2a$10$h1/bM5EYpQK3bR8sL2nP1.KJx8EkM0W9YpV6tZ3Q4X1C5D9m7Hk4m', 'PATIENT', 'PATIENT', 'Marie', '1990-08-22', '+212611111112'),
('Bernard', 'pierre.bernard@email.com', '$2a$10$h1/bM5EYpQK3bR8sL2nP1.KJx8EkM0W9YpV6tZ3Q4X1C5D9m7Hk4m', 'PATIENT', 'PATIENT', 'Pierre', '1978-12-10', '+212611111113'),
('Tanguy', 'sophie.tanguy@email.com', '$2a$10$h1/bM5EYpQK3bR8sL2nP1.KJx8EkM0W9YpV6tZ3Q4X1C5D9m7Hk4m', 'PATIENT', 'PATIENT', 'Sophie', '1995-03-28', '+212611111114'),
('Rousseau', 'luc.rousseau@email.com', '$2a$10$h1/bM5EYpQK3bR8sL2nP1.KJx8EkM0W9YpV6tZ3Q4X1C5D9m7Hk4m', 'PATIENT', 'PATIENT', 'Luc', '1988-07-05', '+212611111115'),
('Petit', 'anne.petit@email.com', '$2a$10$h1/bM5EYpQK3bR8sL2nP1.KJx8EkM0W9YpV6tZ3Q4X1C5D9m7Hk4m', 'PATIENT', 'PATIENT', 'Anne', '1992-11-18', '+212611111116'),
('Durand', 'paul.durand@email.com', '$2a$10$h1/bM5EYpQK3bR8sL2nP1.KJx8EkM0W9YpV6tZ3Q4X1C5D9m7Hk4m', 'PATIENT', 'PATIENT', 'Paul', '1980-02-14', '+212611111117'),
('Fontaine', 'claire.fontaine@email.com', '$2a$10$h1/bM5EYpQK3bR8sL2nP1.KJx8EkM0W9YpV6tZ3Q4X1C5D9m7Hk4m', 'PATIENT', 'PATIENT', 'Claire', '1993-09-30', '+212611111118');

-- Insérer des rendez-vous
INSERT INTO rendez_vous (date_rendez_vous, statut, patient_id, medecin_id) VALUES
('2026-05-20 10:00:00', 'CONFIRME', 2, 1),
('2026-06-10 14:30:00', 'EN_ATTENTE', 2, 1),
('2026-05-22 09:00:00', 'CONFIRME', 3, 2),
('2026-06-05 15:00:00', 'TERMINE', 3, 2),
('2026-05-25 11:30:00', 'CONFIRME', 4, 3),
('2026-06-15 13:00:00', 'EN_ATTENTE', 4, 3),
('2026-05-18 10:00:00', 'TERMINE', 5, 4),
('2026-06-08 16:00:00', 'CONFIRME', 5, 4),
('2026-05-23 12:00:00', 'EN_ATTENTE', 6, 5),
('2026-05-26 14:00:00', 'CONFIRME', 7, 1),
('2026-05-28 10:30:00', 'EN_ATTENTE', 8, 2),
('2026-05-30 15:30:00', 'CONFIRME', 9, 3);

-- Insérer des dossiers médicaux
INSERT INTO dossier_medical (diagnostic, observation, date_creation, patient_id) VALUES
('Hypertension artérielle', 'Tension élevée. Recommandation : réduction du sel et activité physique régulière. Traitement : Lisinopril 10mg/jour.', '2026-01-15', 2),
('Acné modérée', 'Acné hormonale. Traitement recommandé : Nettoyage régulier et cream antibacterienne. Éviter l\'exposition au soleil.', '2026-02-10', 3),
('Entorse à la cheville', 'Entorse modérée du ligament latéral. Repos recommandé 4-6 semaines. Physiotherapie à suivre.', '2026-03-05', 4),
('Migraine chronique', 'Migraines avec aura. Déclencheurs identifiés : stress, manque de sommeil. Traitement : Sumatriptan 50mg au besoin.', '2026-01-20', 5),
('Diabète Type 2', 'Diabète diagnostiqué récemment. Glycémie à jeun : 180 mg/dL. Traitement : Metformine 500mg 2x/jour. Régime spécial.', '2025-12-01', 6),
('Asthme léger', 'Asthme léger bien contrôlé. Inhalateur de secours : Salbutamol. Pas de traitement quotidien nécessaire actuellement.', '2026-02-20', 7),
('Cholestérol élevé', 'Taux de cholestérol LDL : 210 mg/dL. Traitement : Atorvastatine 40mg/jour. Régime pauvre en graisses.', '2025-11-15', 8),
('Anxiety légère', 'Anxiété légère. Pas de trouble panique. Thérapie comportementale recommandée. Si nécessaire : Sertraline 50mg.', '2026-03-10', 9);

COMMIT;


