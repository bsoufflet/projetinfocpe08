package com.webdomotic.core;

public interface Constants {
	
	
	/**
	 * DB properties
	 */
	final static String DBurl = "jdbc:mysql://localhost:8889/webdomotic";
	final static String DBuser = "root";
	final static String DBpass = "root";
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
							{"compte",		"Mon compte",		"Comptes utilisateurs",""}
										};

	/**
	 * Columns name mapping for columns DB
	 */
	final static int FIELD_NAME				=	0;
	//final static int LABEL				=  	1;
	final static int TYPE					=	2;
	
	
	/**
	 * Mapping for columns DB
	 */
	final static String[][] g_mapping_DB_col =	{
							/*Field_name, 		Label, 						Type*/
		
							{"nom",				"Nom",						"text"},
							{"prenom",			"Prénom",					"text"},
							{"login",			"Nom d'utilisateur",		"text"},
							{"motdepass",		"Mot de passe",				"password"},
							{"status",			"Status",					"select"},
							{"ip",				"IP",						"text"},
							{"version",			"Version de la console",	"text"},
							{"mac",				"Adresse MAC",				"text"},
							{"port",			"Port",						"text"},
							{"adresse",			"Adresse",					"text"},
							{"codepostal",		"Code postal",				"text"},
							{"ville",			"Ville",					"text"},
							{"description",		"Description",				"text"},
							{"superficie",		"Superficie",				"text"},
							{"maison_id",		"Maison",					"object"},
							{"utilisateur_id",	"Utilisateur",				"object"},
							{"piece_id",		"Pièce",					"object"},
							
												};

}
