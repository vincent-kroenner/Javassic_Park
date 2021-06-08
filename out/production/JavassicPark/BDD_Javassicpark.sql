

DROP DATABASE IF EXISTS JAVASSICPARK;


CREATE
DATABASE JAVASSICPARK;


USE
JAVASSICPARK;





CREATE TABLE espece
(

    id_espece               Int Auto_increment NOT NULL,

    nomEspece               Varchar(250) NOT NULL,

    tailleEspece            Int          NOT NULL,

    regimeAlimentaireEspece Varchar(250) NOT NULL,

    milieuDeVieEspece       Varchar(250) NOT NULL,
    CONSTRAINT espece_PK PRIMARY KEY (id_espece)

)ENGINE=InnoDB DEFAULT CHARSET = utf8mb4;





CREATE TABLE enclos
(

    id_enclos         Int Auto_increment NOT NULL,

    libelleEnclos     Varchar(250) NOT NULL,

    capaciteMax       Int          NOT NULL,

    capaciteActuelle  Int          NOT NULL,

    tailleMaxOccupant Int          NOT NULL,

    milieuEnclos      Varchar(250) NOT NULL,
    CONSTRAINT enclos_PK PRIMARY KEY (id_enclos)

)ENGINE=InnoDB DEFAULT CHARSET = utf8mb4;





CREATE TABLE Dino
(

    id_dino   Int Auto_increment NOT NULL,

    nomDino   Varchar(250) NOT NULL,

    ageDino   Int          NOT NULL,

    isMalade  BOOLEAN   NOT NULL,

    caractere Varchar(250) NOT NULL,

    id_enclos Int          NOT NULL,

    id_espece Int          NOT NULL,
    CONSTRAINT Dino_PK PRIMARY KEY (id_dino),
    CONSTRAINT Dino_enclos_FK FOREIGN KEY (id_enclos) REFERENCES enclos (id_enclos),
    CONSTRAINT Dino_espece0_FK FOREIGN KEY (id_espece) REFERENCES espece (id_espece)

)ENGINE=InnoDB DEFAULT CHARSET = utf8mb4;





CREATE TABLE soigneur
(

    id_soigneur     Int Auto_increment NOT NULL,

    nomSoigneur     Varchar(250) NOT NULL,

    prenomSoigneur  Varchar(250) NOT NULL,

    ageSoigneur     Int          NOT NULL,

    sexesoigneur    Char(5)      NOT NULL,

    salaireSoigneur Decimal      NOT NULL,

    commentaire     Varchar(250) NULL,
    CONSTRAINT soigneur_PK PRIMARY KEY (id_soigneur)

)ENGINE=InnoDB DEFAULT CHARSET = utf8mb4;






CREATE TABLE soigneur_enclos
(

    id_soigneur Int NOT NULL,

    id_enclos   Int NOT NULL,
    CONSTRAINT soigneur_enclos_PK PRIMARY KEY (id_soigneur, id_enclos),
    CONSTRAINT soigneur_enclos_soigneur_FK FOREIGN KEY (id_soigneur) REFERENCES soigneur (id_soigneur),
    CONSTRAINT soigneur_enclos_enclos0_FK FOREIGN KEY (id_enclos) REFERENCES enclos (id_enclos)

)ENGINE=InnoDB DEFAULT CHARSET = utf8mb4;


INSERT INTO espece
VALUES (null, "Tyrannosaurus Rex", 3, "Carnivore", "Terrestre");

INSERT INTO espece
VALUES (null, "Velociraptor", 2, "Carnivore", "Terrestre");

INSERT INTO espece
VALUES (null, "Dilophosaure", 1, "Carnivore", "Terrestre");

INSERT INTO espece
VALUES (null, "Tricératops", 3, "Herbivore", "Terrestre");

INSERT INTO espece
VALUES (null, "Galliminus", 1, "Herbivore", "Terrestre");

INSERT INTO espece
VALUES (null, "Diplodocus", 4, "Herbivore", "Terrestre");

INSERT INTO espece
VALUES (null, "Parasaurolophus", 2, "Herbivore", "Terrestre");

INSERT INTO espece
VALUES (null, "Ankylosaure", 2, "Herbivore", "Terrestre");

INSERT INTO espece
VALUES (null, "Stegosaure", 3, "Herbivore", "Terrestre");

INSERT INTO espece
VALUES (null, "Ptérodactylus", 2, "Carnivore", "Aérien");

INSERT INTO espece
VALUES (null, "Tupandactylus", 3, "Carnivore", "Aérien");

INSERT INTO espece
VALUES (null, "Mosasaure", 4, "Carnivore", "Aquatique");

INSERT INTO espece
VALUES (null, "Plésiosaure", 4, "Carnivore", "Aquatique");


INSERT INTO enclos
VALUES (null, "Volière", 18, 1, 4, "Aérien");

INSERT INTO enclos
VALUES (null, "Aquarium", 15, 1, 4, "Aquatique");

INSERT INTO enclos
VALUES (null, "Infirmerie", 12, 1, 4, "Infirmerie");

INSERT INTO enclos
VALUES (null, "Sud1", 16, 0, 4, "Terrestre");

INSERT INTO enclos
VALUES (null, "Sud2", 15, 2, 3, "Terrestre");

INSERT INTO enclos
VALUES (null, "Sud3", 17, 0, 2, "Terrestre");

INSERT INTO enclos
VALUES (null, "Ouest1", 15, 2, 3, "Terrestre");

INSERT INTO enclos
VALUES (null, "Ouest2", 10, 0, 2, "Terrestre");

INSERT INTO enclos
VALUES (null, "Nord1", 10, 0, 3, "Terrestre");

INSERT INTO enclos
VALUES (null, "Nord2", 13, 0, 3, "Terrestre");

INSERT INTO enclos
VALUES (null, "Nord3", 14, 0, 3, "Terrestre");

INSERT INTO enclos
VALUES (null, "Est1", 15, 1, 4, "Terrestre");

INSERT INTO enclos
VALUES (null, "Est2", 16, 0, 4, "Terrestre");

INSERT INTO enclos
VALUES (null, "Est3", 14, 0, 2, "Terrestre");


INSERT INTO dino
VALUES (null, "Princesse", 8, false, "Joueur", 12, 1);

INSERT INTO dino
VALUES (null, "Blue", 5, false, "Leader", 7, 2);

INSERT INTO dino
VALUES (null, "Delta", 4, false, "Courageux", 7, 2);

INSERT INTO dino
VALUES (null, "Echo", 3, true, "Blagueur", 3, 2);

INSERT INTO dino
VALUES (null, "Céra", 1, false, "Revêche", 5, 4);

INSERT INTO dino
VALUES (null, "Pointu", 2, false, "Silencieux", 5, 9);

INSERT INTO dino
VALUES (null, "Pétri", 1, false, "Peureux", 1, 10);

INSERT INTO dino
VALUES (null, "Nessy", 10, false, "Discret", 2, 13);



INSERT INTO soigneur
VALUES (null, "Irwin", "Steve", 40, "Homme", 3000, "Passionné par les reptiles, mais pas par les raies");

INSERT INTO soigneur
VALUES (null, "Grylls", "Bear", 36, "Homme", 2000, "Casse-cou professionnel");

INSERT INTO soigneur
VALUES (null, "Horn", "Mike", 56, "Homme", 3500, "Explorateur émérite");

INSERT INTO soigneur
VALUES (null, "Viloteau", "Nicole", 62, "Femme", 3200, "Surnommée la femme aux serpents");

INSERT INTO soigneur
VALUES (null, "Conda", "Anna", 28, "Femme", 1800, "Stagiaire en dressage");

INSERT INTO soigneur
VALUES (null, "Dundee", "Crocodile", 38, "Homme", 2800, "Expert en interventions musclées");