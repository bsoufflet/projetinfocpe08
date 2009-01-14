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
	final static String DB_JNDI_NAME = "MySqlDS";
	
	
	/**
	 * Columns names mapping for modules
	 */
	final static int MODULE					=	0;
	final static int LABEL					=	1;
	final static int ADMIN_LABEL			=	2;
	final static int DB_TABLE				=	3;
	
	/**
	 * Mapping for modules
	 */
	final static String[][] g_mapping_mod =	{
							/*Module_name, 	Label, 				admin_label, 		DB_Table*/
		
							{"console",		"Ma console",		"Consoles",			"consoles"},
							{"maison",		"Ma maison",		"Maisons",			"maisons"},
							{"peripherique","Mes périphériques","Périphériques",	"peripheriques"},
							{"piece",		"Mes pièces",		"Pièces",			"pieces"},
							{"profil",		"Mes profils",		"Profils",			"profils"},
							{"regle",		"Mes règles",		"Règles",			"regles"},
							{"accueil",		"Accueil",			"Accueil",			""},
							{"outil",		"Mes outils",		"Outils",			""},
							{"aide",		"Aide",				"Aide",				""},
							{"compte",		"Mon compte",		"Comptes utilisateurs","utilisateurs"}
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
							{"motdepass",		"Mot de passe",				"password",			"true",				"true"},
							{"statut",			"Statut",					"select",			"false",			"true"},
							{"etat",			"Etat",						"bool",				"true",				"true"},
							{"ip",				"IP",						"text",				"true",				"true"},
							{"version",			"Version de la console",	"text",				"false",			"false"},
							{"mac",				"Adresse MAC",				"text",				"false",			"true"},
							{"port",			"Port",						"text",				"true",				"true"},
							{"adresse",			"Adresse",					"text",				"true",				"true"},
							{"codepostal",		"Code postal",				"text",				"true",				"true"},
							{"ville",			"Ville",					"text",				"true",				"true"},
							{"description",		"Description",				"text",				"true",				"true"},
							{"superficie",		"Superficie",				"text",				"true",				"true"},
							{"maison_id",		"Maison",					"object_maison",	"true",				"true"},
							{"utilisateur_id",	"Utilisateur",				"object_compte",	"true",				"false"},
							{"piece_id",		"Pièce",					"object_piece",		"true",				"true"},
							
												};

}
