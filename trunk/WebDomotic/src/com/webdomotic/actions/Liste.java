package com.webdomotic.actions;

import com.webdomotic.core.*;

public class Liste extends Vue {
	
	private String dataXML;
	private String[][] columnDefs={{"a","b","c","d"},{"1","2","3","4"}};
	
	public String execute() throws Exception {
		System.out.println("Liste Action - module:" + selectedModule);
		/*Object[] result=Hypervisor.getDataXML(selectedModule, selectedAction, "", "");
		dataXML=(String)result[0];
		columnDefs=(String[][])result[1];*/
		dataXML="aaaaa";
		return SUCCESS;
	}
	public void setDataXML(String dataXML){
		this.dataXML=dataXML;
	}
	public String getDataXML(){
		return dataXML;
	}
	public void setColumnDefs(String[][] columnDefs){
		this.columnDefs=columnDefs;
	}
	public String[][] getColumnDefs(){
		return columnDefs;
	}
}