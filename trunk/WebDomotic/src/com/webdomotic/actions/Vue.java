package com.webdomotic.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.webdomotic.core.Hypervisor;


public class Vue extends ActionSupport {
	
	protected String selectedModule;
	protected String selectedModuleLabel;
	protected String selectedAction;
	protected String selectedId;
	protected String isAdmin;
	protected String userId;
	
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
	public String supprimer() {
		System.out.println("Vue - supprimer");
		selectedAction="liste";
		if(Hypervisor.deleteRow(selectedId,selectedModule)){
			addActionMessage("Suppression reussi!");
			return SUCCESS;
		}
		addActionError("Echec de la suppression!");
		return ERROR;
	}
	public String getSelectedModule() {
		return selectedModule;
	}  
	public void setSelectedModule(String selectedModule) {
		this.selectedModule = selectedModule;
	}
	public String getSelectedModuleLabel() {
		return Hypervisor.getLabel_mod(selectedModule);
	}  
	public void setSelectedModuleLabel(String selectedModuleLabel) {
		this.selectedModuleLabel = selectedModuleLabel;
	} 
	public String getSelectedAction() {
		return selectedAction;
	}
	public void setSelectedAction(String selectedAction) {
		this.selectedAction = selectedAction;
	}
	public void setSelectedId(String selectedId){
		this.selectedId=selectedId;
	}
	public String getSelectedId(){
		return selectedId;
	}
	public void setIsAdmin(String isAdmin){
		this.isAdmin=(String)ActionContext.getContext().getSession().get("isadmin");
	}
	public String getIsAdmin(){
		return (String)ActionContext.getContext().getSession().get("isadmin");
	}
	public void setUserId(String userId){
		this.userId=(String)ActionContext.getContext().getSession().get("userid");
	}
	public String getUserId(){
		return (String)ActionContext.getContext().getSession().get("userid");
	}
}