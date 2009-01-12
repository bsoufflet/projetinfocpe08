package com.webdomotic.core;


public class ExampleDB {

	
	
	public static void main(String[] args){
			
			
			ServerDB db = new ServerDB(Constants.DBurl, Constants.DBuser, Constants.DBpass);
			String [][] result = db.queryDB("SELECT * FROM MAISONS");
			
			for(int i=0; i<result.length; i++){
				for(int j=0; j<result[0].length; j++){
					System.out.print(result[i][j]+"\t");
				}
				System.out.println();
			}
			
		}
				


}
