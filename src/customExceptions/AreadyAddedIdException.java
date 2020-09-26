package customExceptions;

@SuppressWarnings("serial")
public class AreadyAddedIdException extends Exception {
	
	private int id;
	private String name;
	
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

	public AreadyAddedIdException(int id, String name) {
		super("In our bank software, " + name + " is already is registred with the id " + id);
		setId(id);
		setName(name);
	}


}
