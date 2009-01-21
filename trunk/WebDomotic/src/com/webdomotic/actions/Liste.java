package com.webdomotic.actions;

import com.webdomotic.core.*;

public class Liste extends Vue {
	
	public String execute() throws Exception {
		System.out.println("Liste Action - module:" + selectedModule);
		Object[] result=Hypervisor.getDataJSArray(selectedModule, "", "");
		dataJSArray=(String)result[0];
		System.out.println("LISTE - donnee de getDataJSArray:"+dataJSArray);
		columnDefs=(String[][])result[1];
		return SUCCESS;
	}
}