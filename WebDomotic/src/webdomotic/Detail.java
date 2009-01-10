package webdomotic;
/**
 * <p>
 * 
 * </p>
 */
public class Detail extends DroiteAction {
	public String execute() throws Exception {
		System.out.println("Detail Action - module:" + selectedModule);
		return SUCCESS;
	}
}

