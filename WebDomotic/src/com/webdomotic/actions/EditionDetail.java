package com.webdomotic.actions;

import com.opensymphony.xwork2.ActionContext;
import com.webdomotic.core.*;

public class EditionDetail extends Vue {
	
	private String[][] fieldToDisplay;
	
	public String execute() throws Exception {
		System.out.println("EditionDetail Action - module:" + selectedModule);
		if(selectedModule.equals("compte") && !isAdmin.equals("true")){
			selectedId=userId;
		}
		if(selectedId != null && !selectedId.equals("")){
			fieldToDisplay=Hypervisor.getDataArray(selectedModule, selectedId, "");
		}else{
			addActionError("Pas de ID et ce n'est pas une creation!");
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String saveToDB(){
		System.out.println("Action: Save");
		if(Hypervisor.saveQuery(ActionContext.getContext().getParameters(),selectedModule))
			return SUCCESS;
		else{
			addActionError("Erreur dans la sauvegarde");
			return ERROR;
		}
	}
	
	
	public void setFieldToDisplay(String[][] fieldToDisplay){
		this.fieldToDisplay=fieldToDisplay;
	}
	public String[][] getFieldToDisplay(){
		return fieldToDisplay;
	}
}