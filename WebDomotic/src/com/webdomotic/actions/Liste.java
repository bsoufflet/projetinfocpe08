package com.webdomotic.actions;

import com.webdomotic.core.*;

public class Liste extends Vue {
	
	private String dataJSArray;
	private String[][] columnDefs;
	
	public String execute() throws Exception {
		System.out.println("Liste Action - module:" + selectedModule);
		Object[] result=Hypervisor.getDataJSArray(selectedModule, "", "");
		dataJSArray=(String)result[0];
		System.out.println("LISTE - donnee de getDataJSArray:"+dataJSArray);
		columnDefs=(String[][])result[1];
		return SUCCESS;
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