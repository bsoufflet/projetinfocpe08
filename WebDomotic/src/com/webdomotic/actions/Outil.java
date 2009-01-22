package com.webdomotic.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.webdomotic.core.HypervisorConsole;


public class Outil extends ActionSupport {
	
	private String identifiant;
	private int ordre;
	
	public String execute() throws Exception {
		System.out.println("Outil - module");
		return SUCCESS;
	}
	public String testPeripherique(){
		System.out.println("Action - testPeripherique - "+identifiant+" - "+String.valueOf(ordre));
		HypervisorConsole.testPeripherique(identifiant, ordre);
		return SUCCESS;
	}
	public void setIdentifiant(String identifiant){
		this.identifiant=identifiant;
	}
	public String getIdentifiant(){
		return identifiant;
	}
	public void setOrdre(int ordre){
		this.ordre=ordre;
	}
	public int getOrdre(){
		return ordre;
	}
}
