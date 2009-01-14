package com.webdomotic.actions;
import com.webdomotic.core.*;

public class EditionDetail extends Vue {
	
	private String[][] fieldToDisplay;
	
	public String execute() throws Exception {
		System.out.println("EditionDetail Action - module:" + selectedModule);
		if(selectedId != null && !selectedId.equals("")){
			fieldToDisplay=Hypervisor.getDataArray(selectedModule, selectedAction, selectedId, "");
		}else{
			addActionError("Pas de ID et ce n'est pas une creation!");
			return ERROR;
		}
		return SUCCESS;
	}
	public void setFieldToDisplay(String[][] fieldToDisplay){
		this.fieldToDisplay=fieldToDisplay;
	}
	public String[][] getFieldToDisplay(){
		return fieldToDisplay;
	}
}