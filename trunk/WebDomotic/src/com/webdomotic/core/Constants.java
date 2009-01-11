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
	 *									Module_name, Label, admin_label, DB_Table
	 */
	final static String[][] g_mapping =	{
											{"console","Ma console","Consoles","consoles"},
											{"maison","Ma maison","Maisons","maisons"},
											{"peripherique","Mes periph�riques","Peripheriques","peripheriques"},
											{"piece","Mes pi�ces","Pi�ces","pieces"},
											{"profil","Mes profils","Profils","profils"},
											{"regle","Mes r�gles","R�gles","regles"},
											{"accueil","Accueil","Accueil",""},
											{"outil","Mes outils","Outils",""},
											{"aide","Aide","Aide",""},
											{"compte","Mon compte","Comptes utilisateurs",""}
										};
	
	

}
