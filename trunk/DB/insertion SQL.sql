/* INSERTION DANS LA TABLE UTILISATEURS */

INSERT INTO `utilisateurs` ( `id` , `login` , `motdepasse` , `nom` , `prenom` , `statut` )
VALUES (
1 , 'sltieche', 'password', 'TIECHE', 'Sarah', 'client'
);
INSERT INTO `utilisateurs` ( `id` , `login` , `motdepasse` , `nom` , `prenom` , `statut` )
VALUES (
2 , 'mgousseff', 'password2', 'GOUSSEFF', 'Marina', 'client'
);
INSERT INTO `utilisateurs` ( `id` , `login` , `motdepasse` , `nom` , `prenom` , `statut` )
VALUES (
3 , 'ncuillery', 'test', 'CUILLERY', 'Nicolas', 'client'
);
INSERT INTO `utilisateurs` ( `id` , `login` , `motdepasse` , `nom` , `prenom` , `statut` )
VALUES (
4 , 'bsoufflet', 'test2', 'SOUFFLET', 'Benjamin', 'administrateur'
);


/* INSERTION DANS LA TABLE MAISONS */

INSERT INTO `maisons` ( `id` , `utilisateurs_id` , `adresse` , `codepostal` , `ville` )
VALUES (
'1', '2', 'rue bidule', '69100', 'VILLEURBANNE'
);
INSERT INTO `maisons` ( `id` , `utilisateurs_id` , `adresse` , `codepostal` , `ville` )
VALUES (
'2', '2', 'rue machin', '69002', 'LYON'
);
INSERT INTO `maisons` ( `id` , `utilisateurs_id` , `adresse` , `codepostal` , `ville` )
VALUES (
'3', '1', 'rue du test', '71008', 'Metropolis'
);
INSERT INTO `maisons` ( `id` , `utilisateurs_id` , `adresse` , `codepostal` , `ville` )
VALUES (
'4', '3', 'rue truc', '69004', 'LYON'
);


/* INSERTION DANS LA TABLE CONSOLES */

INSERT INTO `consoles` ( `id` , `maisons_id` , `ip` , `version` , `mac` )
VALUES (
'1', '1', '192.168.0.1', '1', '00-20-7E-5B-3F-C5'
);
INSERT INTO `consoles` ( `id` , `maisons_id` , `ip` , `version` , `mac` )
VALUES (
'2', '2', '192.168.0.2', '1', '00-19-7F-4B-3A-D5'
);
INSERT INTO `consoles` ( `id` , `maisons_id` , `ip` , `version` , `mac` )
VALUES (
'3', '4', '192.168.0.3', '1', '00-18-3F-2B-FA-D1'
);
INSERT INTO `consoles` ( `id` , `maisons_id` , `ip` , `version` , `mac` )
VALUES (
'4', '3', '192.168.0.4', '1', '00-3D-4E-4B-40-F5'
);


/* INSERTION DANS LA TABLES PIECES */

INSERT INTO `pieces` ( `id` , `maisons_id` , `superficie` )
VALUES (
'1', '1', '12.3'
);
INSERT INTO `pieces` ( `id` , `maisons_id` , `superficie` )
VALUES (
'2', '1', '9'
);
INSERT INTO `pieces` ( `id` , `maisons_id` , `superficie` )
VALUES (
'3', '2', '11'
);
INSERT INTO `pieces` ( `id` , `maisons_id` , `superficie` )
VALUES (
'4', '2', '12.3'
);
INSERT INTO `pieces` ( `id` , `maisons_id` , `superficie` )
VALUES (
'5', '3', '12.3'
);
INSERT INTO `pieces` ( `id` , `maisons_id` , `superficie` )
VALUES (
'6', '3', '10'
);
INSERT INTO `pieces` ( `id` , `maisons_id` , `superficie` )
VALUES (
'7', '4', '14.22'
);


/* INSERTION DANS LA TABLE PERIPHERIQUE */

INSERT INTO `peripheriques` ( `id` , `pieces_id` , `description` )
VALUES (
'1', '1', 'interrupteur'
);
INSERT INTO `peripheriques` ( `id` , `pieces_id` , `description` )
VALUES (
'2', '1', 'capteur de temperature'
);
INSERT INTO `peripheriques` ( `id` , `pieces_id` , `description` )
VALUES (
'3', '2', 'capteur de luminosite'
);
INSERT INTO `peripheriques` ( `id` , `pieces_id` , `description` )
VALUES (
'4', '3', 'interrupteur'
);
INSERT INTO `peripheriques` ( `id` , `pieces_id` , `description` )
VALUES (
'5', '4', 'interrupteur'
);
INSERT INTO `peripheriques` ( `id` , `pieces_id` , `description` )
VALUES (
'6', '5', 'interrupteur'
);
INSERT INTO `peripheriques` ( `id` , `pieces_id` , `description` )
VALUES (
'7', '6', 'interrupteur'
);
INSERT INTO `peripheriques` ( `id` , `pieces_id` , `description` )
VALUES (
'8', '7', 'interrupteur'
);
INSERT INTO `peripheriques` ( `id` , `pieces_id` , `description` )
VALUES (
'9', '5', 'radiateur'
);

/* INSERTION DANS LA TABLE PROFILS */

INSERT INTO `profils` ( `id` , `regles_id` , `utilisateurs_id` , `nom` , `description` , `statut` )
VALUES (
'1', '1', '1', 'Vacances', 'profil à suivre pendant les vacances', '0'
);
INSERT INTO `profils` ( `id` , `regles_id` , `utilisateurs_id` , `nom` , `description` , `statut` )
VALUES (
'2', '2', '1', 'Boulot', 'profil à suivre pendant les journées de travail', '0'
);
INSERT INTO `profils` ( `id` , `regles_id` , `utilisateurs_id` , `nom` , `description` , `statut` )
VALUES (
'3', '3', '1', 'Week-end', 'profil à suivre pendant les week-end', '0'
);
INSERT INTO `profils` ( `id` , `regles_id` , `utilisateurs_id` , `nom` , `description` , `statut` )
VALUES (
'4', '3', '2', 'Vacances', 'profil à suivre pendant les vacances', '0'
);
INSERT INTO `profils` ( `id` , `regles_id` , `utilisateurs_id` , `nom` , `description` , `statut` )
VALUES (
'5', '2', '2', 'Boulot', 'profil à suivre pendant les journées de travail', '0'
);
INSERT INTO `profils` ( `id` , `regles_id` , `utilisateurs_id` , `nom` , `description` , `statut` )
VALUES (
'6', '1', '2', 'Week-end', 'profil à suivre pendant les week-end', '0'
);
INSERT INTO `profils` ( `id` , `regles_id` , `utilisateurs_id` , `nom` , `description` , `statut` )
VALUES (
'7', '2', '3', 'Vacances', 'profil à suivre pendant les vacances', '0'
);
INSERT INTO `profils` ( `id` , `regles_id` , `utilisateurs_id` , `nom` , `description` , `statut` )
VALUES (
'8', '3', '3', 'Boulot', 'profil à suivre pendant les journées de travail', '0'
);
INSERT INTO `profils` ( `id` , `regles_id` , `utilisateurs_id` , `nom` , `description` , `statut` )
VALUES (
'9', '1', '3', 'Week-end', 'profil à suivre pendant les week-end', '0'
);


/* INSERTION DANS LA TABLE REGLES */

INSERT INTO `regles` ( `id` , `description` )
VALUES (
'1', 'blabla'
);
INSERT INTO `regles` ( `id` , `description` )
VALUES (
'2', 'blablabla'
);
INSERT INTO `regles` ( `id` , `description` )
VALUES (
'3', 'bla'
);


