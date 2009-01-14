package com.webdomotic.actions;

import com.opensymphony.xwork2.ActionSupport;


public class Vue extends ActionSupport {
	protected String selectedModule;
	protected String selectedAction;
	
	public String execute() throws Exception {
		System.out.println("Vue - module:" + selectedModule + " - action:" + selectedAction);
		if(selectedModule==null || selectedModule.equals("")){
			System.out.println("default to accueil module");
			selectedModule="accueil";
		}
		if(selectedAction==null || selectedAction.equals("")){
			System.out.println("default to liste action");
			selectedAction="liste";
		}
		
		return SUCCESS;
	}
	public String getSelectedModule() {
		return selectedModule;
	}  
	public void setSelectedModule(String selectedModule) {
		this.selectedModule = selectedModule;
	} 
	public String getSelectedAction() {
		return selectedAction;
	}
	public void setSelectedAction(String selectedAction) {
		this.selectedAction = selectedAction;
	}
}