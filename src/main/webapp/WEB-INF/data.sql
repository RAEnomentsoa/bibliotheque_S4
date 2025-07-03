CREATE TABLE Departement (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

-- Créer la table Employe
CREATE TABLE Employe (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    poste VARCHAR(100) NOT NULL,
    departement_id BIGINT,
    FOREIGN KEY (departement_id) REFERENCES Departement(id) ON DELETE SET NULL
);

-- Insérer des données de test dans la table Departement
INSERT INTO Departement (nom) VALUES
('Informatique'),
('Ressources Humaines'),
('Finance'),
('Marketing');

-- Insérer des données de test dans la table Employe
INSERT INTO Employe (nom, prenom, poste, departement_id) VALUES
('Dupont', 'Jean', 'Développeur', 1),
('Martin', 'Sophie', 'Responsable RH', 2),
('Durand', 'Pierre', 'Analyste Financier', 3),
('Lefèvre', 'Claire', 'Chargée de Marketing', 4);


-- Création de la table Film
CREATE TABLE Film (
    id_film SERIAL PRIMARY KEY,
    titre VARCHAR(100) NOT NULL,
    annee_sortie INTEGER CHECK (annee_sortie >= 1888),
    id_type INTEGER NOT NULL,
    FOREIGN KEY (id_type) REFERENCES Type(id_type) ON DELETE RESTRICT
);
CREATE TABLE Type (
    id_type SERIAL PRIMARY KEY,
    nom_type VARCHAR(50) NOT NULL UNIQUE
    
);

-- Création de la table Categorie
CREATE TABLE Categorie (
    id_categorie SERIAL PRIMARY KEY,
    nom_categorie VARCHAR(50) NOT NULL UNIQUE
);
CREATE TABLE Film_Categorie (
    id_Film_Categorie SERIAL PRIMARY KEY,
    id_Film INTEGER NOT NULL,
    id_Categorie INTEGER NOT NULL,
    FOREIGN KEY (id_Film) REFERENCES Film(id_film) ON DELETE CASCADE,
    FOREIGN KEY (id_Categorie) REFERENCES Categorie(id_categorie) ON DELETE CASCADE
);
INSERT INTO Type (nom_type) VALUES
('Long métrage'),
('Court métrage'),
('Documentaire');

-- Insertion de données dans la table Categorie
INSERT INTO Categorie (nom_categorie) VALUES
('Action'),
('Comédie'),
('Drame'),
('Science-fiction'),
('Horreur');

-- Insertion de données dans la table Film
INSERT INTO Film (titre, annee_sortie, id_type) VALUES
('Inception', 2010, 1),
('The Grand Budapest Hotel', 2014, 1),
('An Inconvenient Truth', 2006, 3),
('Get Out', 2017, 1),
('La Jetée', 1962, 2);

-- Insertion de données dans la table Film_Categorie
INSERT INTO Film_Categorie (id_Film, id_Categorie) VALUES
(1, 1), -- Inception -> Action
(1, 4), -- Inception -> Science-fiction
(2, 2), -- The Grand Budapest Hotel -> Comédie
(2, 3), -- The Grand Budapest Hotel -> Drame
(3, 3), -- An Inconvenient Truth -> Drame
(4, 3), -- Get Out -> Drame
(4, 5), -- Get Out -> Horreur
(5, 4); -- La Jetée -> Science-fiction