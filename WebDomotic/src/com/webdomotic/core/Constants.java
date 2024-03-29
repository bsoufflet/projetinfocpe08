package com.webdomotic.core;

public interface Constants {
	
	
	/**
	 * DB properties
	 * le port par defaut pour la base de donnee est 3306 
	 * pour le MAC: c un truc comme 8889 bizzard....
	 */
	final static String DBurl = "jdbc:mysql://localhost:3306/webdomotic";
	final static String DBuser = "jboss";
	final static String DBpass = "jboss";
	
	
	/**
	 * Columns names mapping for modules
	 */
	final static int MODULE					=	0;
	final static int LABEL					=	1;
	final static int ADMIN_LABEL			=	2;
	final static int DB_TABLE				=	3;
	final static int RELATED_MODULES		=	4;
	final static int RELATION_FIELD			=	5;
	
	/**
	 * Mapping for modules
	 */
	final static String[][] g_mapping_mod =	{
							/*Module_name, 	Label, 				admin_label, 		DB_Table			related_modules			relation_field*/
		
							{"console",		"Ma console",		"Consoles",			"consoles",			"",						""},
							{"maison",		"Ma maison",		"Maisons",			"maisons",			"piece,console",		"maison_id"},
							{"peripherique","Mes périphériques","Périphériques",	"peripheriques",	"",						""},
							{"piece",		"Mes pièces",		"Pièces",			"pieces",			"peripherique",			"piece_id"},
							{"profil",		"Mes profils",		"Profils",			"profils",			"",						""},
							{"regle",		"Mes règles",		"Règles",			"regles",			"",						""},
							{"accueil",		"Accueil",			"Accueil",			"",					"",						""},
							{"outil",		"Mes outils",		"Outils",			"",					"",						""},
							{"aide",		"Aide",				"Aide",				"",					"",						""},
							{"compte",		"Mon compte",		"Comptes utilisateurs","utilisateurs",	"maison,profil,regle",	"utilisateur_id"}
										};

	/**
	 * Columns name mapping for columns DB
	 */
	final static int FIELD_NAME				=	0;
	//final static int LABEL				=  	1;
	final static int TYPE					=	2;
	final static int EDIT_RIGHT				=	3;
	final static int VIEW_RIGHT				=	4;
	
	
	/**
	 * Mapping for columns DB
	 */
	final static String[][] g_mapping_DB_col =	{
							/*Field_name, 		Label, 						Type			edit_right			view_right*/
		
							{"nom",				"Nom",						"text",				"true",				"true"},
							{"prenom",			"Prénom",					"text",				"true",				"true"},
							{"login",			"Nom d'utilisateur",		"text",				"true",				"true"},
							{"motdepasse",		"Mot de passe",				"password",			"true",				"true"},
							{"statut",			"Statut",					"select",			"false",			"true"},
							{"etat",			"Etat",						"bool",				"true",				"true"},
							{"ip",				"IP",						"text",				"true",				"true"},
							{"version",			"Version de la console",	"text",				"false",			"false"},
							{"mac",				"Adresse MAC",				"text",				"false",			"true"},
							{"port",			"Port",						"number",			"true",				"true"},
							{"adresse",			"Adresse",					"text",				"true",				"true"},
							{"identifiant",		"Identifiant",				"text",				"true",				"true"},
							{"codepostal",		"Code postal",				"number",			"true",				"true"},
							{"ville",			"Ville",					"text",				"true",				"true"},
							{"description",		"Description",				"text",				"true",				"true"},
							{"superficie",		"Superficie",				"number",			"true",				"true"},
							{"periode",			"Période",					"periode",			"true",				"true"},
							{"maison_id",		"Maison",					"object_maison",	"true",				"true"},
							{"utilisateur_id",	"Utilisateur",				"object_compte",	"true",				"false"},
							{"piece_id",		"Pièce",					"object_piece",		"true",				"true"},
							
												};
	
	
	/**
	 * Columns name mapping for n-n relationships
	 */
	final static int RELATIONSHIP_NAME		=	0;
	final static int FIELD1					=	1;
	final static int FIELD2					=	2;
	//final static int DB_TABLE				=	3;
	
	
	/**
	 * Mapping for columns for n-n relationships
	 */
	final static String[][] g_mapping_DB_rel =	{
							/*Relationship_name, 			Field1, 			Field2					DB_table*/
		
							{"regles_peripheriques",		"regle_id",			"peripherique_id",		"regles_peripheriques"},
							{"profils_regles",				"profil_id",		"regle_id",				"profils_regles"},
							
												};

}
