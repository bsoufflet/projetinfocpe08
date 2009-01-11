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
	 *									Module_name, Label, DB_Table
	 */
	final static String[][] g_mapping =	{
											{"","Ma Console","CONSOLES"},
											{"","Ma Maison","MAISON"},
											{"","","PERIPHERIQUES"},
											{"","","PIECES"},
											{"","","PROFILES"},
											{"","","REGLES"},
											{"","","UTILISATEURS"},
											{"","","REGLES"}
										};
	
	

}
