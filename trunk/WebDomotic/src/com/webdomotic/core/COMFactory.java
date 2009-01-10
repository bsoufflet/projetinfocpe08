package com.webdomotic.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



final public class COMFactory {


	private static int nb_sockets;
	private static int nb_conDB;

	private COMFactory(){	
	}

	public static ServerDB getInstanceDB(){
		nb_conDB++;
		return new ServerDB();
	}

	public static ConsoleSocket getInstanceSoc(String host, int port){
		nb_sockets++;
		return new ConsoleSocket(host,port);
	}

}

final class ConsoleSocket{

	private Socket socket;
	private OutputStream out;
	//private BufferedWriter out1;
	private BufferedReader in; 
	
	
	public ConsoleSocket(String host, int port){

		try {
			socket = new Socket(host, port);
			out = socket.getOutputStream();
	//		out1 = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			in =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void write(char c){
		try {
			out.write(c);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void write(String s){
		try {
			out.write(s.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void write(int i){
		try {
			out.write(i);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void write(byte [] data){
		try {
			out.write(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public String read() throws IOException{
		return in.readLine();
	}
}

final class ServerDB{

private Connection DBconnector;
	
	
	/**
	 * Connect to DB
	 */
	public ServerDB(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			DBconnector = DriverManager.getConnection("jdbc:mysql://localhost:3306/webdomotic", "root",null);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Connexion:"+ DBconnector);
	}
	
	
	/**
	 *	Helper function to assemble query string
	 */
	public static String genQuery(String[] s){
		
		return null;
	}
	
	/**
	 * Query typical select command
	 */
	public ResultSet queryDB(String query){	  
			try {
				Statement st = DBconnector.createStatement();
				return st.executeQuery(query);
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
	}
	/**
	 * Query for update,insert,delete command
	 */
	public int UpdateDB(String query){
		try {
			Statement st = DBconnector.createStatement();
			return st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * close DB connection
	 */
	public void close(){
		try {
			if(!DBconnector.isClosed())
				DBconnector.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
