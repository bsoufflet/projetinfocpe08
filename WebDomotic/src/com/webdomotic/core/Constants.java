package com.webdomotic.core;

public interface Constants {
	
	
	/**
	 * DB properties
	 */
	final static String DBurl = "jdbc:mysql://localhost:3306/webdomotic";
	final static String DBuser = "root";
	final static String DBpass = null;
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
	//final static int LABEL				=	1;
	final static int TYPE					=	2;
	
	
	/**
	 * Mapping for columns DB
	 */
	final static String[][] g_mapping_DB_col =	{
							/*Field_name, 	Label, 				Type*/
		
							{"motdepass",	"Mot de pass",		"password"},
							{"motdepass",	"Mot de pass",		"password"},
							{"motdepass",	"Mot de pass",		"password"},
							{"motdepass",	"Mot de pass",		"password"}
							
										};

}
