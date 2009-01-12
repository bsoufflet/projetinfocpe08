package com.webdomotic.actions;

import com.webdomotic.core.*;

public class Liste extends Vue {
	private String listeDonnee; 
	public String execute() throws Exception {
		System.out.println("Liste Action - module:" + selectedModule);
		retrieveData();
		return SUCCESS;
	}
	protected String[][] retrieveData(){
		ServerDB db = new ServerDB();
		return db.queryDB("SELECT * FROM MAISONS");
	}
	public void construireListe(){

		
	}
}