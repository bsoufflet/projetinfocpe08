package com.webdomotic.core;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ExampleDB {

	
	public static void main(String[] args) throws SQLException{
			
			
			ServerDB db = new ServerDB(Constants.DBurl,Constants.DBuser,Constants.DBpass);
			ResultSet rs = db.queryDB("SELECT * FROM MAISONS");
			
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
	        
	        for(int i=1; i<nb_rows+1; i++){
	        	rs.next();
				for(int j=0; j<nb_columns; j++){
					result[i][j] = rs.getString(j+1);
					System.out.println(rsmd.getColumnName(j+1)+": "+rs.getString(j+1));
				}
				System.out.println();
					
			}
			
			System.out.println();
		}
				


}
