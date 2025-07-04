
INSERT INTO Bibliothecaire (nom, mot_de_passe) VALUES ('Alice Dupont', 'password123');
INSERT INTO Bibliothecaire (nom, mot_de_passe) VALUES ('Jean Martin', 'securepass');

-- Bibliothecaire
INSERT INTO Bibliothecaire (nom, mot_de_passe) VALUES
('admin', 'admin123'),
('dupont', 'password1');

-- TypeAdherent
INSERT INTO TypeAdherent (libelle, quotaExemplaire, dureePret, dureeProlongement, dureePenalite, quotaReservation) VALUES
('Etudiant', 3, 15, 7, 5, 2),
('Professeur', 5, 30, 14, 3, 5),
('Professionnel', 10, 60, 30, 1, 10);

-- Adherent
INSERT INTO Adherent (nom, email, mot_de_passe, type_id, date_naissance) VALUES
('Alice Martin', 'alice.martin@email.com', 'alicepass', 1, '2000-05-10'),
('Bob Durand', 'bob.durand@email.com', 'bobpass', 2, '1985-11-23');

-- Livre
INSERT INTO Livre (nom, restriction) VALUES
('Le Petit Prince', FALSE),
('L Étranger', TRUE);

-- Exemplaire
INSERT INTO Exemplaire (livre_id) VALUES
(1),
(1),
(2);

-- EtatExemplaire
INSERT INTO EtatExemplaire (libelle) VALUES
('Neuf'),
('Bon état'),
('Abîmé');

-- StatutExemplaire
INSERT INTO StatutExemplaire (exemplaire_id, etat_id, bibliothecaire_id, date_changement) VALUES
(1, 1, 1, '2024-01-10'),
(2, 2, 2, '2024-02-15');

-- Abonnement
INSERT INTO Abonnement (adherent_id, date_debut, date_fin) VALUES
(1, '2024-01-01', '2024-12-31'),
(2, '2024-03-01', '2025-02-28');

-- TypePret
INSERT INTO TypePret (libelle) VALUES
('Normal'),
('Longue durée');

-- Pret
INSERT INTO Pret (adherent_id, exemplaire_id, date_pret, date_retour, type_id) VALUES
(1, 1, '2024-06-01', NULL, 1),
(2, 3, '2024-06-10', '2024-06-20', 2);

-- Prolongement
INSERT INTO Prolongement (pret_id) VALUES
(1);

-- Reservation
INSERT INTO Reservation (adherent_id, exemplaire_id, date_reservation) VALUES
(2, 2, '2024-06-15');

-- Penalite
INSERT INTO Penalite (pret_id) VALUES
(2);

-- JourFerie
INSERT INTO JourFerie (date_ferie, evenement) VALUES
('2024-12-25', 'Noël'),
('2024-01-01', 'Nouvel An');

-- JourOuvrable
INSERT INTO JourOuvrable (jour_semaine) VALUES
('Lundi'),
('Mardi'),
('Mercredi'),
('Jeudi'),
('Vendredi');