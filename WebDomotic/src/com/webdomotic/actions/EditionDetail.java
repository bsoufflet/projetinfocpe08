package com.webdomotic.actions;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.webdomotic.core.*;

public class EditionDetail extends Vue {
	
	private String[][] fieldToDisplay;
	private String[][] resultPeriph;
	private Object[] resultGetObject = new Object[4];

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
	
	public String getObjectName(){
		
		Map request = ActionContext.getContext().getParameters();
		
		resultGetObject[0] = Hypervisor.getData(Hypervisor.getModuleName_mod(((String[])request.get("module"))[0]));
		resultGetObject[1] = ""+((String[])request.get("id"))[0];//pour le selected par defaut
		resultGetObject[2] = ""+((String[])request.get("module"))[0];//pour le name du select
		if(request.containsKey("selectedAction")){
			resultGetObject[3] = ""+((String[])request.get("selectedAction"))[0];// pour differencier l'action	
		}else{
			resultGetObject[3] = "";
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
	public void setResultGetObject(Object[] resultGetObject){
		this.resultGetObject=resultGetObject;
	}
	public Object[] getResultGetObject(){
		return resultGetObject;
	}
	
}