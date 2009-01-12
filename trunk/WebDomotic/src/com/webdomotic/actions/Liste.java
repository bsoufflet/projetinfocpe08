package com.webdomotic.actions;

import com.webdomotic.core.*;

public class Liste extends Vue {
	
	private String dataXML;
	
	public String execute() throws Exception {
		System.out.println("Liste Action - module:" + selectedModule);
		dataXML=Hypervisor.getDataXML(selectedModule, selectedAction, "", "");
		return SUCCESS;
	}
	public void setDataXML(String dataXML){
		this.dataXML=dataXML;
	}
	public String getDataXML(){
		return dataXML;
	}
}