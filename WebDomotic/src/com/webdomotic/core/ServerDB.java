package com.webdomotic.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public final class ServerDB{

	private Connection DBconnector;
		
		/**
		 * Connect to DB
		 */
		public ServerDB(){
			try {
				Class.forName("com.mysql.jdbc.Driver");
				DBconnector = DriverManager.getConnection(Constants.DBurl,Constants.DBuser,Constants.DBpass);
				/*
				Context ctx = new InitialContext();
				DataSource ds = (DataSource)ctx.lookup("jdbc/"+Constants.DB_JNDI_NAME);
				DBconnector = ds.getConnection();
				*/
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Query typical select command
		 */
		public String[][] queryDB(String query){
				System.out.println("QUERY: "+query);
				try {
					Statement st = DBconnector.createStatement();
					ResultSet rs = st.executeQuery(query);
					return resultToArray(rs);
				} catch (SQLException e) {
					e.printStackTrace();
					return null;
				}
		}
		/**
		 * Query for update,insert,delete command
		 */
		public int UpdateDB(String query){
			System.out.println("QUERY: "+query);
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