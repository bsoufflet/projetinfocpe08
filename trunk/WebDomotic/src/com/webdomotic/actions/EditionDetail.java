package com.webdomotic.actions;

import com.opensymphony.xwork2.ActionContext;
import com.webdomotic.core.*;

public class EditionDetail extends Vue {
	
	private String[][] fieldToDisplay;
	private String[][] resultPeriph;

	public String execute() throws Exception {
		System.out.println("EditionDetail Action - module:" + selectedModule);
		if(selectedModule.equals("compte") && !isAdmin.equals("true")){
			selectedId=userId;
		}
		if(selectedId != null && !selectedId.equals("")){
			fieldToDisplay=Hypervisor.getDataArray(selectedModule, selectedId, "");
			// Pour le subpanel de regle (relation n-n)
			if(selectedModule.equals("regle")){
				System.out.println("GetPanelData Action - module:" + selectedModule);
				Object[] result=Hypervisor.getDataPanelJSArray(selectedModule, selectedId);
				dataJSArray=(String)result[0];
				System.out.println("GetPanelData - donnee de GetPanelData:"+dataJSArray);
				columnDefs=(String[][])result[1];
				resultPeriph=Hypervisor.getData("peripherique");
			}
		}else{
			System.out.println("PROBLEM PAS DE ID");
			addActionError("Pas de ID et ce n'est pas une creation!");
			return ERROR;
		}
		return SUCCESS;
	}
	public String saveToDB(){
		System.out.println("Action: Save");
		selectedId = Hypervisor.saveQuery(ActionContext.getContext().getParameters(),selectedModule,selectedId);
		if(selectedId.equals("-1")){
			addActionError("Cet item ne vous appartiens pas");
			return ERROR;
		}
		selectedAction = "detail";
		//return "test";
		return SUCCESS;
	}
	public String addRelationship(){
		System.out.println("Action: addRelationship");
		String returnId = Hypervisor.addRelationship(ActionContext.getContext().getParameters());
		if(returnId.equals("-1")){
			addActionError("Cet item ne vous appartiens pas");
			System.out.println("addRelationship: ERROR");
			return ERROR;
		}
		selectedAction = "detail";
		System.out.println("addRelationship: SUCCESS");
		return SUCCESS;
	}
	public void setFieldToDisplay(String[][] fieldToDisplay){
		this.fieldToDisplay=fieldToDisplay;
	}
	public String[][] getFieldToDisplay(){
		return fieldToDisplay;
	}
	public void setResultPeriph(String[][] resultPeriph){
		this.resultPeriph=resultPeriph;
	}
	public String[][] getResultPeriph(){
		return resultPeriph;
	}
}