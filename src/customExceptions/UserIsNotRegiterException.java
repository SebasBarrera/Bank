package customExceptions;

@SuppressWarnings("serial")
public class UserIsNotRegiterException extends Exception {
	
	private int id;
	private String name;
	
	public UserIsNotRegiterException(int id, String name) {
		super("The user " + name + "with the id " + id + ", is not register in our bank software, please register the user");
		setId(id);
		setName(name);
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
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

}
