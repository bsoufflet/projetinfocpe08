package com.webdomotic.core;

import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.Date;

public class HypervisorConsole extends Thread {
	private static ServerDB db;
	
	private static byte[] filedata;
	
	public HypervisorConsole() {
	}
	
	public static void envoyerOrdres(String userid) throws IOException {
		Vector<String> t_ordres = construireOrdres(userid);
		String nom_fichier = construireFichier(t_ordres);
		envoyerFichier(nom_fichier);
		
	}
	
	private static void envoyerFichier(String nom_fichier) throws IOException {
		String acquittement = "";
		//FileInputStream fis = new FileInputStream(nom_fichier);
		FileInputStream fis = new FileInputStream("test.txt");
		int num = fis.available();

		filedata = new byte[num];
		fis.read(filedata);
		
		ConsoleSocket socket = new ConsoleSocket("192.168.0.30", 2000);
		socket.write('d');
		socket.write(intToByteArray(filedata.length));
		socket.write(filedata);
		System.out.println("Fin envoi");
		while(acquittement != "ok") {
			acquittement = socket.read();
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Acquittement :" + acquittement);
		}
		System.out.println("Sortie");
		//System.out.println(socket.read());
	}
	
	public static byte[] intToByteArray(int value) {
        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            int offset = (b.length - 1 - i) * 8;
            b[i] = (byte) ((value >>> offset) & 0xFF);
        }
        return b;
    }
	
	private static String construireFichier(Vector<String> t_ordres) throws IOException {
		Date D = new Date();
		long temps = D.getTime();
		String nom_fichier = temps+".txt";
		FileWriter fichier = new FileWriter(nom_fichier);
		BufferedWriter out = new BufferedWriter(fichier);
		for(int i=0; i<t_ordres.size(); i++){
			out.write(t_ordres.get(i));
			out.newLine();
			System.out.println(t_ordres.get(i));
		}
		out.close();
		return nom_fichier;
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