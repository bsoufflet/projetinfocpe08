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
		String acquittement = null;
		FileInputStream fis = new FileInputStream(nom_fichier);
		int num = fis.available();
		int byte_envoyes = 0;
		filedata = new byte[num];
		byte [] a_envoyer;
		fis.read(filedata);
		
		System.out.println("Debut envoi");
		ConsoleSocket socket = new ConsoleSocket("192.168.0.30", 2000);
		socket.write('d');
		socket.write(intToByteArray(filedata.length));
		//Envoi des paquets :
		while(byte_envoyes<num) {
			System.out.println("byte_envoyes : "+byte_envoyes+" et num : "+num);
			acquittement = null;
			if(num - byte_envoyes > 64) { // On envoie 64 caractères
				a_envoyer = new byte[64];
				for(int i = 0 ; i < 64 ; i++) {
					a_envoyer[i] = filedata[i+byte_envoyes];
				}
				byte_envoyes = byte_envoyes + 64;
			}
			else { // On envoie ce qui reste (dernier paquet)
				a_envoyer = new byte[num - byte_envoyes];
				for(int i = 0 ; i < num - byte_envoyes ; i++) {
					a_envoyer[i] = filedata[i+byte_envoyes];
				}
				byte_envoyes = num;
			}
			System.out.println("ecriture : "+a_envoyer.length+"octets");
			socket.write(a_envoyer);
			a_envoyer = null;
			// Attente de l'acquittement :
			while(acquittement == null) {
				acquittement = socket.read();
				System.out.println("acquittement : "+acquittement);
			}
		}
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