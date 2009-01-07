package webdomotic;

import com.opensymphony.xwork2.ActionSupport;

/**
 * <p>
 * Validate a user login.
 * </p>
 */
public class DroiteAction extends ActionSupport {
	private String selectedModule;
	public String execute() throws Exception {
		System.out.println("DroiteAction");
		if(selectedModule==null || selectedModule.equals("")){
			System.out.println("default to accueil");
			selectedModule="accueil";
		}
		return SUCCESS;
	}
	public String getSelectedModule() {  
		return selectedModule;  
	}  
	public void setSelectedModule(String selectedModule) {  
		this.selectedModule = selectedModule;  
	} 
}