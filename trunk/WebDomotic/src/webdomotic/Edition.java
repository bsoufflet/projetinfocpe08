package webdomotic;
/**
 * <p>
 * 
 * </p>
 */
public class Edition extends Vue {
	public String execute() throws Exception {
		System.out.println("Edition Action - module:" + selectedModule);
		return SUCCESS;
	}
}
