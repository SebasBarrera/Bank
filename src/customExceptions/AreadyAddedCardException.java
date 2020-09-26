package customExceptions;

@SuppressWarnings("serial")
public class AreadyAddedCardException extends Exception {
	
	private long number;
	private String name;
	
	/**
	 * @return the number
	 */
	public long getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(long number) {
		this.number = number;
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

	public AreadyAddedCardException(long number, String name) {
		super("In our bank software, " + name + " is already is registred with the number " + number);
		setNumber(number);
		setName(name);
	}


}