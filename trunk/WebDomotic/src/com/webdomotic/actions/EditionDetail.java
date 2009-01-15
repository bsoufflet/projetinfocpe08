package com.webdomotic.actions;

import com.opensymphony.xwork2.ActionContext;
import com.webdomotic.core.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

public class EditionDetail extends Vue implements ServletRequestAware,ServletResponseAware{

	private String[][] fieldToDisplay;

	private HttpServletRequest request;
	private HttpServletResponse response;

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
		if(Hypervisor.saveQuery(ActionContext.getContext().getParameters(),selectedModule)){
			selectedAction = "detail";
			return SUCCESS;
		}
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

	public void setServletRequest(HttpServletRequest request){
		this.request = request;
	}

	public HttpServletRequest getServletRequest(){
		return request;
	}

	public void setServletResponse(HttpServletResponse response){
		this.response = response;
	}

	public HttpServletResponse getServletResponse(){
		return response;
	}
}