/* INSERTION DANS LA TABLE UTILISATEURS */

INSERT INTO `utilisateurs` ( `id` , `login` , `motdepasse` , `nom` , `prenom` , `statut` )
VALUES (
1 , 'sltieche', md5('password'), 'TIECHE', 'Sarah', 'client'
);
INSERT INTO `utilisateurs` ( `id` , `login` , `motdepasse` , `nom` , `prenom` , `statut` )
VALUES (
2 , 'mgousseff', md5('password2'), 'GOUSSEFF', 'Marina', 'client'
);
INSERT INTO `utilisateurs` ( `id` , `login` , `motdepasse` , `nom` , `prenom` , `statut` )
VALUES (
3 , 'ncuillery', md5('test'), 'CUILLERY', 'Nicolas', 'client'
);
INSERT INTO `utilisateurs` ( `id` , `login` , `motdepasse` , `nom` , `prenom` , `statut` )
VALUES (
4 , 'bsoufflet', md5('test2'), 'SOUFFLET', 'Benjamin', 'administrateur'
);


/* INSERTION DANS LA TABLE MAISONS */

INSERT INTO `maisons` ( `id` , `utilisateur_id` , `nom`, `adresse` , `codepostal` , `ville` )
VALUES (
1, 2,'maison principale', 'rue bidule', '69100', 'VILLEURBANNE'
);
INSERT INTO `maisons` ( `id` , `utilisateur_id`, `nom` , `adresse` , `codepostal` , `ville` )
VALUES (
2, 2,'maison secondaire', 'rue machin', '69002', 'LYON'
);
INSERT INTO `maisons` ( `id` , `utilisateur_id`, `nom` , `adresse` , `codepostal` , `ville` )
VALUES (
3, 1,'maison principale', 'rue du test', '71008', 'Metropolis'
);
INSERT INTO `maisons` ( `id` , `utilisateur_id`, `nom` , `adresse` , `codepostal` , `ville` )
VALUES (
4, 3,'maison principale', 'rue truc', '69004', 'LYON'
);


/* INSERTION DANS LA TABLE CONSOLES */

INSERT INTO `consoles` ( `id` , `maison_id` , `nom` ,`ip` , `version` , `mac`, `port`)
VALUES (
1, 1, 'console1', '192.168.0.1', '1', '00-20-7E-5B-3F-C5', 1
);
INSERT INTO `consoles` ( `id` , `maison_id` , `nom` ,`ip` , `version` , `mac`, `port` )
VALUES (
2, 2, 'console2', '192.168.0.2', '1', '00-19-7F-4B-3A-D5', 2
);
INSERT INTO `consoles` ( `id` , `maison_id` , `nom` ,`ip` , `version` , `mac`, `port` )
VALUES (
3, 4, 'console3', '192.168.0.3', '1', '00-18-3F-2B-FA-D1', 4
);
INSERT INTO `consoles` ( `id` , `maison_id` , `nom` ,`ip` , `version` , `mac`, `port` )
VALUES (
4, 3, 'console4', '192.168.0.4', '1', '00-3D-4E-4B-40-F5', 3
);


/* INSERTION DANS LA TABLES PIECES */

INSERT INTO `pieces` ( `id` , `maison_id`, `nom` , `superficie` )
VALUES (
1, 1, 'salle de bain', 12.3
);
INSERT INTO `pieces` ( `id` , `maison_id`, `nom` , `superficie` )
VALUES (
2, 1, 'cuisine', 9
);
INSERT INTO `pieces` ( `id` , `maison_id`, `nom` , `superficie` )
VALUES (
3, 2, 'salon', 11
);
INSERT INTO `pieces` ( `id` , `maison_id`, `nom` , `superficie` )
VALUES (
4, 2, 'salle de bain', 12.3
);
INSERT INTO `pieces` ( `id` , `maison_id`, `nom` , `superficie` )
VALUES (
5, 3, 'salon', 12.3
);
INSERT INTO `pieces` ( `id` , `maison_id`, `nom` , `superficie` )
VALUES (
6, 3, 'salle a manger', 10
);
INSERT INTO `pieces` ( `id` , `maison_id`, `nom` , `superficie` )
VALUES (
7, 4, 'salon', 14.22
);


/* INSERTION DANS LA TABLE PERIPHERIQUE */

INSERT INTO `peripheriques` ( `id` , `piece_id`, `nom`, `identifiant`, `description` )
VALUES (
1, 1, 'interrupteur', 'A02','peripherique pour reglage d une lumiere'
);
INSERT INTO `peripheriques` ( `id` , `piece_id`, `nom`, `identifiant` , `description` )
VALUES (
2, 1, 'capteur de temperature', 'C13', 'peripherique pour reglage de la temperature'
);
INSERT INTO `peripheriques` ( `id` , `piece_id`, `nom`, `identifiant` , `description` )
VALUES (
3, 2, 'capteur de luminosite', 'E12', 'peripherique pour reglage d une lumiere'
);
INSERT INTO `peripheriques` ( `id` , `piece_id`, `nom`, `identifiant` , `description` )
VALUES (
4, 3, 'interrupteur', 'A04', 'peripherique pour reglage d une lumiere'
);
INSERT INTO `peripheriques` ( `id` , `piece_id`, `nom`, `identifiant` , `description` )
VALUES (
5, 4, 'interrupteur', 'B01', 'peripherique pour reglage d une lumiere'
);
INSERT INTO `peripheriques` ( `id` , `piece_id`, `nom`, `identifiant` , `description` )
VALUES (
6, 5, 'interrupteur', 'B02', 'peripherique pour reglage d une lumiere'
);
INSERT INTO `peripheriques` ( `id` , `piece_id`, `nom`, `identifiant` , `description` )
VALUES (
7, 6, 'interrupteur', 'B03', 'peripherique pour reglage d une lumiere'
);
INSERT INTO `peripheriques` ( `id` , `piece_id`, `nom`, `identifiant` , `description` )
VALUES (
8, 7, 'interrupteur', 'C11', 'peripherique pour reglage d une lumiere'
);
INSERT INTO `peripheriques` ( `id` , `piece_id`, `nom`, `identifiant` , `description` )
VALUES (
9, 5, 'radiateur', 'A12', 'peripherique pour reglage de la temperature'
);


/* INSERTION DANS LA TABLE PROFILS */

INSERT INTO `profils` ( `id` , `utilisateur_id` , `nom` , `description` , `etat` )
VALUES (
1, 1, 'Vacances', 'profil à suivre pendant les vacances', '0'
);
INSERT INTO `profils` ( `id` , `utilisateur_id` , `nom` , `description` , `etat` )
VALUES (
2, 1, 'Boulot', 'profil à suivre pendant les journées de travail', '0'
);
INSERT INTO `profils` ( `id` , `utilisateur_id` , `nom` , `description` , `etat` )
VALUES (
3, 1, 'Week-end', 'profil à suivre pendant les week-end', '0'
);
INSERT INTO `profils` ( `id` , `utilisateur_id` , `nom` , `description` , `etat` )
VALUES (
4, 2, 'Vacances', 'profil à suivre pendant les vacances', '0'
);
INSERT INTO `profils` ( `id` , `utilisateur_id` , `nom` , `description` , `etat` )
VALUES (
5, 2, 'Boulot', 'profil à suivre pendant les journées de travail', '0'
);
INSERT INTO `profils` ( `id` , `utilisateur_id` , `nom` , `description` , `etat` )
VALUES (
6, 2, 'Week-end', 'profil à suivre pendant les week-end', '0'
);
INSERT INTO `profils` ( `id` , `utilisateur_id` , `nom` , `description` , `etat` )
VALUES (
7, 3, 'Vacances', 'profil à suivre pendant les vacances', '0'
);
INSERT INTO `profils` ( `id` , `utilisateur_id` , `nom` , `description` , `etat` )
VALUES (
8, 3, 'Boulot', 'profil à suivre pendant les journées de travail', '0'
);
INSERT INTO `profils` ( `id` , `utilisateur_id` , `nom` , `description` , `etat` )
VALUES (
9, 3, 'Week-end', 'profil à suivre pendant les week-end', '0'
);


/* INSERTION DANS LA TABLE REGLES */

INSERT INTO `regles` ( `id` , `utilisateur_id` , `nom` ,`description`, `periode`, `etat` )
VALUES (
1, 1, 'regle1', 'blabla', '2,3,5-13h30-60-10', 0 
);
INSERT INTO `regles` ( `id` , `utilisateur_id` , `nom` ,`description`, `periode`, `etat` )
VALUES (
2, 1, 'regle2' , 'blablabla', '5-12h09-120-0', 0
);
INSERT INTO `regles` ( `id` , `utilisateur_id` , `nom` ,`description`, `periode`, `etat` )
VALUES (
3, 1, 'regle3', 'bla', '5-12h09-120-0', 1
);
INSERT INTO `regles` ( `id` , `utilisateur_id` , `nom` ,`description`, `periode`, `etat` )
VALUES (
4, 2, 'regle1', 'blabla', '2,3,5-13h30-60-10', 0
);
INSERT INTO `regles` ( `id` , `utilisateur_id` , `nom` ,`description`, `periode`, `etat` )
VALUES (
5, 2, 'regle2' , 'blablabla', '2,5-0h0-0-0', 1
);
INSERT INTO `regles` ( `id` , `utilisateur_id` , `nom` ,`description`, `periode`, `etat` )
VALUES (
6, 2, 'regle3', 'bla', '3-1h7-0-150', 1
);
INSERT INTO `regles` ( `id` , `utilisateur_id` , `nom` ,`description`, `periode`, `etat` )
VALUES (
7, 3, 'regle1', 'blabla', '2,5-0h0-0-0', 0
);
INSERT INTO `regles` ( `id` , `utilisateur_id` , `nom` ,`description`, `periode`, `etat` )
VALUES (
8, 3, 'regle2' , 'blablabla', '2,3,5-13h30-60-10', 0
);
INSERT INTO `regles` ( `id` , `utilisateur_id` , `nom` ,`description`, `periode`, `etat` )
VALUES (
9, 3, 'regle3', 'bla', '3-1h7-0-150', 1
);



/* INSERTION DANS LA TABLE PROFILS_REGLES */

INSERT INTO `profils_regles` (`id`, `profil_id`, `regle_id`) 
VALUES(
1, 1, 2
);
INSERT INTO `profils_regles` (`id`, `profil_id`, `regle_id`) 
VALUES(
2, 2, 3
);
INSERT INTO `profils_regles` (`id`, `profil_id`, `regle_id`) 
VALUES(
3, 3, 1
);
INSERT INTO `profils_regles` (`id`, `profil_id`, `regle_id`) 
VALUES(
4, 4, 4
);
INSERT INTO `profils_regles` (`id`, `profil_id`, `regle_id`) 
VALUES(
5, 5, 4
);
INSERT INTO `profils_regles` (`id`, `profil_id`, `regle_id`) 
VALUES(
6, 6, 5
);
INSERT INTO `profils_regles` (`id`, `profil_id`, `regle_id`) 
VALUES(
7, 6, 6
);
INSERT INTO `profils_regles` (`id`, `profil_id`, `regle_id`) 
VALUES(
8, 1, 3
);
INSERT INTO `profils_regles` (`id`, `profil_id`, `regle_id`) 
VALUES(
9, 7, 7
);
INSERT INTO `profils_regles` (`id`, `profil_id`, `regle_id`) 
VALUES(
10, 8, 8
);
INSERT INTO `profils_regles` (`id`, `profil_id`, `regle_id`) 
VALUES(
11, 9, 9
);
INSERT INTO `profils_regles` (`id`, `profil_id`, `regle_id`) 
VALUES(
12, 9, 7
);

/* Insertion dans la table regles_peripheriques */

INSERT INTO `regles_peripheriques` ( `id` , `regle_id` , `peripherique_id` )
VALUES (
'1', '1', '1'
);
INSERT INTO `regles_peripheriques` ( `id` , `regle_id` , `peripherique_id` )
VALUES (
'2', '2', '1'
);
INSERT INTO `regles_peripheriques` ( `id` , `regle_id` , `peripherique_id` )
VALUES (
'3', '3', '3'
);
INSERT INTO `regles_peripheriques` ( `id` , `regle_id` , `peripherique_id` )
VALUES (
'4', '4', '6'
);
INSERT INTO `regles_peripheriques` ( `id` , `regle_id` , `peripherique_id` )
VALUES (
'5', '5', '5'
);