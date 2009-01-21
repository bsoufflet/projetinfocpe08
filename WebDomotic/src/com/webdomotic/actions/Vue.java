package com.webdomotic.actions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.webdomotic.core.Hypervisor;


public class Vue extends ActionSupport {
	
	protected String selectedModule;
	protected String selectedModuleLabel;
	protected String selectedAction;
	protected String selectedId;
	protected String lastId;
	protected String actionType;
	protected String isAdmin;
	protected String userId;
	protected String dataJSArray;
	protected String[][] columnDefs;
	
	public String execute() throws Exception {
		System.out.println("Vue - module:" + selectedModule + " - action:" + selectedAction);
		if(selectedModule==null || selectedModule.equals("")){
			System.out.println("default to accueil module");
			selectedModule="accueil";
		}
		if(selectedAction==null || selectedAction.equals("")){
			System.out.println("default to liste action");
			selectedAction="accueil";
		}
		return SUCCESS;
	}
	
	public String supprimer() {
		System.out.println("Vue - supprimer");
		
		if(actionType.equals("relationship")){
			// c'est une suppression de relation
			selectedAction="detail";
			if(Hypervisor.deleteRelationship(lastId,selectedId,"regles_peripheriques")){
				System.out.println("Vue - supprimer - Suppression de la relation - Succes");
				addActionMessage("Suppression de la relation reussi!");
				return SUCCESS;
			}else{
				addActionError("Echec de la suppression de la relation!");
				System.out.println("Vue - supprimer relation - ECHEC");
				return ERROR;
			}
		}
		selectedAction="liste";
		if(Hypervisor.deleteRow(selectedId,selectedModule)){
			addActionMessage("Suppression reussi!");
			return SUCCESS;
		}
		addActionError("Echec de la suppression!");
		System.out.println("Vue - supprimer - ECHEC");
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
	public void setLastId(String lastId){
		this.lastId=lastId;
	}
	public String getLastId(){
		return lastId;
	}
	public void setActionType(String actionType){
		this.actionType=actionType;
	}
	public String getActionType(){
		return actionType;
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
	public void setDataJSArray(String dataJSArray){
		this.dataJSArray=dataJSArray;
	}
	public String getDataJSArray(){
		return dataJSArray;
	}
	public void setColumnDefs(String[][] columnDefs){
		this.columnDefs=columnDefs;
	}
	public String[][] getColumnDefs(){
		return columnDefs;
	}
}