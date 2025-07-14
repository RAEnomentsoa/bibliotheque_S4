CREATE TABLE Bibliothecaire (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL
);

CREATE TABLE TypeAdherent (
    id INT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(100) NOT NULL,
    quotaExemplaire INT NOT NULL,
    dureePret INT NOT NULL,
    dureeProlongement INT NOT NULL,
    dureePenalite INT NOT NULL,
    quotaReservation INT NOT NULL
);

CREATE TABLE Adherent (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    mot_de_passe VARCHAR(255) NOT NULL,
    type_id INT NOT NULL,
    date_naissance DATE NOT NULL,
    FOREIGN KEY (type_id) REFERENCES TypeAdherent(id)
);

CREATE TABLE Livre (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) NOT NULL,
    restriction BOOLEAN DEFAULT FALSE
);

CREATE TABLE Exemplaire (
    id INT PRIMARY KEY AUTO_INCREMENT,
    livre_id INT NOT NULL,
    FOREIGN KEY (livre_id) REFERENCES Livre(id)
);


------------------------------------------------

CREATE TABLE EtatExemplaire (
    id INT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE StatutExemplaire (
    id INT PRIMARY KEY AUTO_INCREMENT,
    exemplaire_id INT NOT NULL,
    etat_id INT NOT NULL,
    bibliothecaire_id INT NOT NULL,
    date_changement DATE NOT NULL,
    FOREIGN KEY (exemplaire_id) REFERENCES Exemplaire(id),
    FOREIGN KEY (etat_id) REFERENCES EtatExemplaire(id),
    FOREIGN KEY (bibliothecaire_id) REFERENCES Bibliothecaire(id)
);

CREATE TABLE Abonnement (
    id INT PRIMARY KEY AUTO_INCREMENT,
    adherent_id INT NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    FOREIGN KEY (adherent_id) REFERENCES Adherent(id)
);

CREATE TABLE TypePret (
    id INT PRIMARY KEY AUTO_INCREMENT,
    libelle VARCHAR(100) NOT NULL
);

CREATE TABLE Pret (
    id INT PRIMARY KEY AUTO_INCREMENT,
    adherent_id INT NOT NULL,
    exemplaire_id INT NOT NULL,
    date_pret DATE NOT NULL,
    date_retour DATE,
    type_id INT NOT NULL,
    FOREIGN KEY (adherent_id) REFERENCES Adherent(id),
    FOREIGN KEY (exemplaire_id) REFERENCES Exemplaire(id),
    FOREIGN KEY (type_id) REFERENCES TypePret(id)
);

CREATE TABLE Prolongement (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pret_id INT NOT NULL,
    FOREIGN KEY (pret_id) REFERENCES Pret(id)
);

CREATE TABLE Reservation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    adherent_id INT NOT NULL,
    exemplaire_id INT NOT NULL,
    date_reservation DATE NOT NULL,
    FOREIGN KEY (adherent_id) REFERENCES Adherent(id),
    FOREIGN KEY (exemplaire_id) REFERENCES Exemplaire(id)
);

CREATE TABLE Penalite (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pret_id INT NOT NULL,
    FOREIGN KEY (pret_id) REFERENCES Pret(id)
);

CREATE TABLE JourFerie (
    id INT PRIMARY KEY AUTO_INCREMENT,
    date_ferie DATE NOT NULL,
    evenement VARCHAR(255)
);

CREATE TABLE JourOuvrable (
    id INT PRIMARY KEY AUTO_INCREMENT,
    jour_semaine VARCHAR(15) NOT NULL -- Ex: 'Lundi', 'Mardi', ...
);

-- must impliment
+----+------------+
| id | libelle    |
+----+------------+
|  1 | libre      |
|  2 | emprunté   |
|  3 | réservé    |
|  4 | en_attente |
+----+------------+