package com.webdomotic.core;

import java.util.Vector;

public class HypervisorConsole extends Thread {
	private static ServerDB db;
	
	public HypervisorConsole() {
	}
	
	public static void envoyerOrdres(String userid) {
		Vector<String> t_ordres = construireOrdres(userid);
		for(int i=0; i<t_ordres.size(); i++){
			// Envoi de l'ordre à la carte par la socket.
			
			
		}
	}
	
	private static Vector<String> construireOrdres(String userid) {
		Vector<String> t_ordres = new Vector<String>();
		String[][] t_regles = recupererRegles(userid);
		int index = 0;
		for(int i=0; i<t_regles.length; i++){
			// Generation des ordres à partir des regles :
			t_ordres.add(t_regles[i][3]); //champs description de la table
			index++;
		}
		return t_ordres;
	}
	
	private static String[][] recupererRegles(String userid){
        String query="SELECT * FROM regles WHERE utilisateur_id = '"+userid+"'";
        String [][] result = db.queryDB(query);
        return result;
    }
	
	
	
}