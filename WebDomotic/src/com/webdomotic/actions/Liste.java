package com.webdomotic.actions;

public class Liste extends Vue {
	public String execute() throws Exception {
		System.out.println("Liste Action - module:" + selectedModule);
		return SUCCESS;
	}
}