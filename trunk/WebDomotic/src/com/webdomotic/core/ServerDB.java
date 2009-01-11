package com.webdomotic.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

final class ServerDB{

	private Connection DBconnector;
		
		/**
		 * Connect to DB
		 */
		public ServerDB(String DBurl, String user, String pass){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				DBconnector = DriverManager.getConnection(DBurl,user,pass);
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
		
		
		private String[][] resultToArray(ResultSet rs) throws SQLException{
			
			//need to check was null
			
			//Get result set meta data
	        ResultSetMetaData rsmd = rs.getMetaData();
	        
	        //get number of columns
	        int nb_columns = rsmd.getColumnCount();
	        
	        //get number of rows
	        rs.last();
	        int nb_rows = rs.getRow();
	        rs.beforeFirst();
	        
	        //create array
	        String[][] result = new String[nb_rows+1][nb_columns];
	        
	        //fill row[0] with the columns name
	        for(int j=0; j<nb_columns; j++){
				result[0][j] = rsmd.getColumnName(j+1);
				System.out.println(rsmd.getColumnName(j+1));
			}
	        
	        //fill data
	        for(int i=1; i<nb_rows+1; i++){
	        	rs.next();
				for(int j=0; j<nb_columns; j++){
					result[i][j] = rs.getString(j+1);
				}	
			}
		
			return result;
		}

	}