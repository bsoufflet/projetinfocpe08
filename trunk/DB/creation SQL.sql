CREATE TABLE utilisateurs (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  login VARCHAR(10) NULL,
  motdepasse VARCHAR(32) NULL,
  nom VARCHAR(30) NULL,
  prenom VARCHAR(20) NULL,
  statut VARCHAR(20) NULL,
  PRIMARY KEY(id)
);

CREATE TABLE profils (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  utilisateur_id INTEGER UNSIGNED NOT NULL,
  nom VARCHAR(30) NULL,
  description VARCHAR(60) NULL,
  etat TINYINT(1) UNSIGNED NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(utilisateur_id)
    REFERENCES utilisateurs(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE regles (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  utilisateur_id INTEGER UNSIGNED NOT NULL,
  nom VARCHAR(30) NULL,
  description VARCHAR(60) NULL,
  periode VARCHAR(100) NULL,
  etat TINYINT(1) UNSIGNED NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(utilisateur_id)
    REFERENCES utilisateurs(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE maisons (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  utilisateur_id INTEGER UNSIGNED NOT NULL,
  nom VARCHAR(30) NULL,
  adresse VARCHAR(20) NULL,
  codepostal INT UNSIGNED NULL,
  ville VARCHAR(20) NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(utilisateur_id)
    REFERENCES utilisateurs(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);

CREATE TABLE consoles (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  maison_id INTEGER UNSIGNED NOT NULL,
  nom VARCHAR(30) NULL,
  ip VARCHAR(15) NULL,
  version VARCHAR(10) NULL,
  mac VARCHAR(17) NULL,
  port INTEGER UNSIGNED NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(maison_id)
    REFERENCES maisons(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);

CREATE TABLE profils_regles (
  id INTEGER UNSIGNED NOT NULL,
  profil_id INTEGER UNSIGNED NOT NULL,
  regle_id INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(profil_id)
    REFERENCES profils(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(regle_id)
    REFERENCES regles(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE pieces (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  maison_id INTEGER UNSIGNED NOT NULL,
  nom VARCHAR(30) NULL,
  superficie DOUBLE NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(maison_id)
    REFERENCES maisons(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);

CREATE TABLE peripheriques (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  piece_id INTEGER UNSIGNED NOT NULL,
  nom VARCHAR(30) NULL,
  identifiant VARCHAR(3) NULL,
  description VARCHAR(60) NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(piece_id)
    REFERENCES pieces(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);

CREATE TABLE regles_peripheriques (
  id INTEGER UNSIGNED NOT NULL,
  regle_id INTEGER UNSIGNED NOT NULL,
  peripherique_id INTEGER UNSIGNED NOT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(regle_id)
    REFERENCES regles(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(peripherique_id)
    REFERENCES peripheriques(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);


