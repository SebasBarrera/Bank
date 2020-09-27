package customExceptions;

@SuppressWarnings("serial")
public class ActionsOnInActiveException extends Exception {
	
	private String name;
	private long aC;

	public ActionsOnInActiveException(String name, long aC) {
		super("MESSASGE");//TODO
		this.aC = aC;
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the aC
	 */
	public long getaC() {
		return aC;
	}

	/**
	 * @param aC the aC to set
	 */
	public void setaC(long aC) {
		this.aC = aC;
	}

}
