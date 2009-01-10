package com.webdomotic.actions;

public class Detail extends Vue {
	public String execute() throws Exception {
		System.out.println("Detail Action - module:" + selectedModule);
		return SUCCESS;
	}
}