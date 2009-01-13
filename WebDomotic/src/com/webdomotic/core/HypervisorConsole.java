package com.webdomotic.core;

public class HypervisorConsole extends Thread {
	private static ServerDB db;
	
	public HypervisorConsole() {
	}
	
	private static String[] construireOrdres(String userid) {
		String[] t_ordres = null;
		String[][] t_regles = recupRegles(userid);
		int index = 0;
		for(int i=0; i<t_regles.length; i++){
			// Generation des ordres à partir des regles :
			t_ordres[index] = t_regles[i][3]; //champs description de la table
		}
		return t_ordres;
	}
	
	public static void envoyerOrdres(String userid) {
		
	}
	
	private static String[][] recupRegles(String userid){
        String query="SELECT * FROM regles WHERE utilisateur_id = '"+userid+"'";
        String [][] result = db.queryDB(query);
        return result;
    }
	
	
	
}