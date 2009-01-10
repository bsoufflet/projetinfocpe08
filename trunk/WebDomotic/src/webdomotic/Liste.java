package webdomotic;
/**
 * <p>
 * 
 * </p>
 */
public class Liste extends DroiteAction {
	public String execute() throws Exception {
		System.out.println("Liste Action - module:" + selectedModule);
		return SUCCESS;
	}
}
