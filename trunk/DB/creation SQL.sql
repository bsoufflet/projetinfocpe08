CREATE TABLE regles (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  description VARCHAR(60) NULL,
  PRIMARY KEY(id)
);

CREATE TABLE utilisateurs (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  login VARCHAR(10) NULL,
  motdepasse VARCHAR(10) NULL,
  nom VARCHAR(20) NULL,
  prenom VARCHAR(20) NULL,
  statut VARCHAR(10) NULL,
  PRIMARY KEY(id)
);

CREATE TABLE maisons (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  utilisateurs_id INT UNSIGNED NULL,
  adresse VARCHAR(20) NULL,
  codepostal INT UNSIGNED NULL,
  ville VARCHAR(20) NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(utilisateurs_id)
    REFERENCES utilisateurs(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);

CREATE TABLE consoles (
  id INT NOT NULL AUTO_INCREMENT,
  maisons_id INT UNSIGNED NOT NULL,
  ip VARCHAR(15) NULL,
  version VARCHAR(10) NULL,
  mac VARCHAR(17) NULL,
  port INT NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(maisons_id)
    REFERENCES maisons(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);

CREATE TABLE profils (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  regles_id INTEGER UNSIGNED NOT NULL,
  utilisateurs_id INT UNSIGNED NOT NULL,
  nom VARCHAR(10) NULL,
  description VARCHAR(60) NULL,
  statut INTEGER UNSIGNED NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(utilisateurs_id)
    REFERENCES utilisateurs(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(regles_id)
    REFERENCES regles(id)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION
);

CREATE TABLE pieces (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  maisons_id INT UNSIGNED NOT NULL,
  superficie DOUBLE NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(maisons_id)
    REFERENCES maisons(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);

CREATE TABLE peripheriques (
  id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT,
  pieces_id INTEGER UNSIGNED NOT NULL,
  description VARCHAR(60) NULL,
  PRIMARY KEY(id),
  FOREIGN KEY(pieces_id)
    REFERENCES pieces(id)
      ON DELETE CASCADE
      ON UPDATE CASCADE
);


