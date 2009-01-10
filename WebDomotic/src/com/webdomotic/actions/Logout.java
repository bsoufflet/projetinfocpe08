package com.webdomotic.actions;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ActionContext;
import java.util.*;

public class Logout extends ActionSupport {
  public String execute() throws Exception {
	System.out.println("Logout");
    Map session = ActionContext.getContext().getSession();
    session.put("authorized","no");
    return SUCCESS;
    }
}