INSERT INTO userapp (nom, email, password, role) VALUES
('admin', 'admin@healthcare.com', '$2a$10$SlVZQcXvLx7RBL2gd9K.pOqfV9tpuWIlnI8YBWqN.OQD4q5G8Ksfa', 'ADMIN'), -- password: admin123
('medecin1', 'medecin1@healthcare.com', '$2a$10$Rv.8ZsKI.BdxJmJJ3c.ZeOJ8gfVJVIbI8cJbF3OkW0aPHV9iJHzba', 'MEDECIN'), -- password: medecin123
('medecin2', 'medecin2@healthcare.com', '$2a$10$Rv.8ZsKI.BdxJmJJ3c.ZeOJ8gfVJVIbI8cJbF3OkW0aPHV9iJHzba', 'MEDECIN'), -- password: medecin123
('patient1', 'patient1@healthcare.com', '$2a$10$h1/bM5EYpQK3bR8sL2nP1.KJx8EkM0W9YpV6tZ3Q4X1C5D9m7Hk4m', 'PATIENT'), -- password: patient123
('jean.dupont', 'jean.dupont@healthcare.com', '$2a$10$h1/bM5EYpQK3bR8sL2nP1.KJx8EkM0W9YpV6tZ3Q4X1C5D9m7Hk4m', 'PATIENT'); -- password: patient123

INSERT INTO medecin (nom, specialite, email, telephone) VALUES
('Dr. Ahmed Hassan', 'Cardiologie', 'dr.ahmed@clinic.com', '+212612345678'),
('Dr. Fatima Benali', 'Dermatologie', 'dr.fatima@clinic.com', '+212612345679'),
('Dr. Mohamed El Khatib', 'Orthopedie', 'dr.elkhattib@clinic.com', '+212612345680'),
('Dr. Leila Morocco', 'Neurologie', 'dr.leila@clinic.com', '+212612345681'),
('Dr. Karim Saadi', 'Radiologie', 'dr.karim@clinic.com', '+212612345682');

INSERT INTO patient (nom, prenom, email, telephone, date_naissance) VALUES
('Dupont', 'Jean', 'jean.dupont@email.com', '+212611111111', '1985-05-15'),
('Martin', 'Marie', 'marie.martin@email.com', '+212611111112', '1990-08-22'),
('Bernard', 'Pierre', 'pierre.bernard@email.com', '+212611111113', '1978-12-10'),
('Tanguy', 'Sophie', 'sophie.tanguy@email.com', '+212611111114', '1995-03-28'),
('Rousseau', 'Luc', 'luc.rousseau@email.com', '+212611111115', '1988-07-05'),
('Petit', 'Anne', 'anne.petit@email.com', '+212611111116', '1992-11-18'),
('Durand', 'Paul', 'paul.durand@email.com', '+212611111117', '1980-02-14'),
('Fontaine', 'Claire', 'claire.fontaine@email.com', '+212611111118', '1993-09-30');

INSERT INTO rendez_vous (date_rendez_vous, statut, patient_id, medecin_id) VALUES
-- Rendez-vous pour Jean Dupont (Patient 1) avec Dr. Ahmed Hassan (Medecin 1)
('2026-05-20 10:00:00', 'CONFIRME', 1, 1),
('2026-06-10 14:30:00', 'EN_ATTENTE', 1, 1),

-- Rendez-vous pour Marie Martin (Patient 2) avec Dr. Fatima Benali (Medecin 2)
('2026-05-22 09:00:00', 'CONFIRME', 2, 2),
('2026-06-05 15:00:00', 'TERMINE', 2, 2),

-- Rendez-vous pour Pierre Bernard (Patient 3) avec Dr. Mohamed El Khatib (Medecin 3)
('2026-05-25 11:30:00', 'CONFIRME', 3, 3),
('2026-06-15 13:00:00', 'EN_ATTENTE', 3, 3),

-- Rendez-vous pour Sophie Tanguy (Patient 4) avec Dr. Leila Morocco (Medecin 4)
('2026-05-18 10:00:00', 'TERME', 4, 4),
('2026-06-08 16:00:00', 'CONFIRME', 4, 4),

-- Rendez-vous pour Luc Rousseau (Patient 5) avec Dr. Karim Saadi (Medecin 5)
('2026-05-23 12:00:00', 'EN_ATTENTE', 5, 5),

-- Rendez-vous pour Anne Petit (Patient 6) avec Dr. Ahmed Hassan (Medecin 1)
('2026-05-26 14:00:00', 'CONFIRME', 6, 1),

-- Rendez-vous pour Paul Durand (Patient 7) avec Dr. Fatima Benali (Medecin 2)
('2026-05-28 10:30:00', 'EN_ATTENTE', 7, 2),

-- Rendez-vous pour Claire Fontaine (Patient 8) avec Dr. Mohamed El Khatib (Medecin 3)
('2026-05-30 15:30:00', 'CONFIRME', 8, 3);

INSERT INTO dossier_medical (diagnostic, observation, date_creation, patient_id) VALUES
('Hypertension artérielle', 'Tension élevée. Recommandation : réduction du sel et activité physique régulière. Traitement : Lisinopril 10mg/jour.', '2026-01-15', 1),
('Acné modérée', 'Acné hormonale. Traitement recommandé : Nettoyage régulier et cream antibacterienne. Éviter l\'exposition au soleil.', '2026-02-10', 2),
('Entorse à la cheville', 'Entorse modérée du ligament latéral. Repos recommandé 4-6 semaines. Physiotherapie à suivre.', '2026-03-05', 3),
('Migraine chronique', 'Migraines avec aura. Déclencheurs identifiés : stress, manque de sommeil. Traitement : Sumatriptan 50mg au besoin.', '2026-01-20', 4),
('Diabète Type 2', 'Diabète diagnostiqué récemment. Glycémie à jeun : 180 mg/dL. Traitement : Metformine 500mg 2x/jour. Régime spécial.', '2025-12-01', 5),
('Asthme léger', 'Asthme léger bien contrôlé. Inhalateur de secours : Salbutamol. Pas de traitement quotidien nécessaire actuellement.', '2026-02-20', 6),
('Cholestérol élevé', 'Taux de cholestérol LDL : 210 mg/dL. Traitement : Atorvastatine 40mg/jour. Régime pauvre en graisses.', '2025-11-15', 7),
('Anxiety légère', 'Anxiété légère. Pas de trouble panique. Thérapie comportementale recommandée. Si nécessaire : Sertraline 50mg.', '2026-03-10', 8);

-- ============================================================
-- Fin des insertions de données de test
-- ============================================================
COMMIT;


