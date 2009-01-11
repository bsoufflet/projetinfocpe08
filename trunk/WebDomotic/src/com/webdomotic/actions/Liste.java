package com.webdomotic.actions;

import com.webdomotic.core.*;

public class Liste extends Vue {
	private String listeDonnee; 
	public String execute() throws Exception {
		System.out.println("Liste Action - module:" + selectedModule);
		retrouverData();
		return SUCCESS;
	}
	public void retrouverData(){
		ServerDB db = new ServerDB();
		String [][] result = db.queryDB("SELECT * FROM MAISONS");
		
		for(int i=0; i<result.length; i++){
			for(int j=0; j<result[0].length; j++){
				System.out.print(result[i][j]+"\t");
			}
			System.out.println();
		}
	}
	public void construireListe(){

		
	}
}