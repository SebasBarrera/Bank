package customExceptions;

@SuppressWarnings("serial")
public class NothingToUndoException extends Exception {
	
	private String name;
	
	public NothingToUndoException(String name) {
		super("The user " + name + " has not performed any action");
		this.setName(name);
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

}
