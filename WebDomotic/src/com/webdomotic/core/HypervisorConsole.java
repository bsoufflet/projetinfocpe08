package com.webdomotic.core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.Date;

public class HypervisorConsole extends Thread {
	private static ServerDB db;
	
	public HypervisorConsole() {
	}
	
	public static void envoyerOrdres(String userid) {
		Date D = new Date();
		Vector<String> t_ordres = construireOrdres(userid);
		try {
			FileWriter fichier = new FileWriter(userid+"_"+D.getTime()+".txt");
			BufferedWriter out = new BufferedWriter(fichier);
			for(int i=0; i<t_ordres.size(); i++){
			// Envoi de l'ordre à la carte par la socket.
				out.write(t_ordres.get(i));
				out.newLine();
				System.out.println(t_ordres.get(i));
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Vector<String> construireOrdres(String userid) {
		Vector<String> t_ordres = new Vector<String>();
		String[][] t_regles = recupererRegles(userid);
		int index = 0;
		for(int i=1; i<t_regles.length; i++){
			// Generation des ordres à partir des regles :
			t_ordres.add(t_regles[i][3]); //champs description de la table
			index++;
		}
		return t_ordres;
	}
	
	private static String[][] recupererRegles(String userid){
        String query="SELECT * FROM regles WHERE utilisateur_id = '"+userid+"'";
        db = new ServerDB();
        String [][] result = db.queryDB(query);
        db.close();
        return result;
    }
	
	
	
}