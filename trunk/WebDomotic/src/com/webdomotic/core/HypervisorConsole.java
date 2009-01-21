package com.webdomotic.core;

import java.io.FileInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.Date;
import java.util.Calendar;


public class HypervisorConsole extends Thread {
	private static ServerDB db;
	
	private static byte[] filedata;
	
	private static Calendar c = Calendar.getInstance();
	private static Date d = c.getTime();
	private static long timestamp = d.getTime();
		
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
		String nom_fichier = (timestamp/1000) +".txt";
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
		String[] regle,jours,heure;
		long ordre_timestamp, prout;
		int ordre_minute, ordre_heure;
		
		t_ordres.add(String.valueOf(timestamp/1000));
		for(int i=1; i<t_regles.length; i++){
			// Generation des ordres à partir des regles :
			regle = t_regles[i][4].split("-");
			jours = regle[0].split(",");
			for(int j= 0 ; j < jours.length ; j++) {
				//Traitement du jour :
				ordre_timestamp = 24*3600*1000*calculerEcartJours(Integer.parseInt(jours[j]));
				ordre_timestamp = ordre_timestamp + timestamp;
				
				//Traitement des minutes :
				heure = regle[1].split("h");
				ordre_heure = Integer.parseInt(heure[0]);
				ordre_minute = Integer.parseInt(heure[1]);
				ordre_timestamp = ordre_timestamp + 60*1000*calculerEcartMinutes(ordre_heure,ordre_minute);
				
				//Traitement de la répétition dans la journée :
				if(!regle[2].equals("0")) {
					for(int k=ordre_heure*60+ordre_minute; k<24*60; k = k + Integer.parseInt(regle[2])) {
						prout = ordre_timestamp + 60*1000*k;
						//Traitement de la durée : (cas périodique à la journée)
						if(!regle[3].equals("0")) {
							t_ordres.add(String.valueOf(prout/1000) + ";A01;" + t_regles[i][5]);
							System.out.println(t_regles[i][4] + " - jour " + jours[j] + " ordre : " + String.valueOf(prout) + ";A01;" + t_regles[i][5]);
							prout = prout + 60*1000*Integer.parseInt(regle[3]);
							if(t_regles[i][5].equals("0")) {
								t_ordres.add(String.valueOf(prout/1000) + ";A01;1");
								System.out.println(t_regles[i][4] + " - jour " + jours[j] + " ordre : " + String.valueOf(prout) + ";A01;1");
							}
							if(t_regles[i][5].equals("1")) {
								t_ordres.add(String.valueOf(prout/1000) + ";A01;0");
								System.out.println(t_regles[i][4] + " - jour " + jours[j] + " ordre : " + String.valueOf(prout) + ";A01;0");	
							}
						} else {
							t_ordres.add(String.valueOf(prout/1000) + ";A01;" + t_regles[i][5]);
							System.out.println(t_regles[i][4] + " - jour " + jours[j] + " ordre : " + String.valueOf(prout) + ";A01;" + t_regles[i][5]);
						}
						
					}
				} else {
					//Traitement de la durée : (cas non périodique à la journée)
					prout = ordre_timestamp;
					if(!regle[3].equals("0")) {
						t_ordres.add(String.valueOf(prout/1000) + ";A01;" + t_regles[i][5]);
						System.out.println(t_regles[i][4] + " - jour " + jours[j] + " ordre : " + String.valueOf(prout) + ";A01;" + t_regles[i][5]);
						prout = prout + 60*1000*Integer.parseInt(regle[3]);
						if(t_regles[i][5].equals("0")) {
							t_ordres.add(String.valueOf(prout/1000) + ";A01;1");
							System.out.println(t_regles[i][4] + " - jour " + jours[j] + " ordre : " + String.valueOf(prout) + ";A01;1");	
						}
						if(t_regles[i][5].equals("1")) {
							t_ordres.add(String.valueOf(prout/1000) + ";A01;0");
							System.out.println(t_regles[i][4] + " - jour " + jours[j] + " ordre : " + String.valueOf(prout) + ";A01;0");
						}
					} else {
						t_ordres.add(String.valueOf(prout/1000) + ";A01;" + t_regles[i][5]);
						System.out.println(t_regles[i][4] + " - jour " + jours[j] + " ordre : " + String.valueOf(prout) + ";A01;" + t_regles[i][5]);
					}
					
				}
				
			}
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
	
	public static int calculerEcartJours(int jour_ordre) {
		int aujourdhui = c.get(Calendar.DAY_OF_WEEK);
		if(jour_ordre < aujourdhui) {
			jour_ordre = jour_ordre + 7;
		}
		int ecart = jour_ordre - aujourdhui;
		return ecart;
	}
	
	public static int calculerEcartMinutes(int ordre_hour, int ordre_minute) {
		int aujourdhui_minute = c.get(Calendar.MINUTE);
		int aujourdhui_hour = c.get(Calendar.HOUR);
		if(c.get(Calendar.AM_PM) == 1) {
			aujourdhui_hour = aujourdhui_hour + 12;
		}
		int aujourdhui_minutes = aujourdhui_hour*60+aujourdhui_minute;
		int ordres_minutes = ordre_hour*60+ordre_minute;
		int ecart = ordres_minutes - aujourdhui_minutes;
		return ecart;
	}
	
}