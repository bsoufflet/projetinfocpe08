package com.webdomotic.core;

public interface Constants {
	
	
	/**
	 * DB properties
	 */
	final static String DBurl = "jdbc:mysql://localhost:3306/webdomotic";
	final static String DBuser = "root";
	final static String DBpass = null;
	
	/**
	 * Mapping
	 */
	final static String[][] g_mapping =	{
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
	

}
