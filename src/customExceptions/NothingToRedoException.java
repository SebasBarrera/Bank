package customExceptions;

@SuppressWarnings("serial")
public class NothingToRedoException extends Exception {
	
	private String name;
	
	public NothingToRedoException(String name) {
		super("The user " + name + " has not undo any action");
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
