package customExceptions;

@SuppressWarnings("serial")
public class NotFoundCardException extends Exception{
	
	private String name;
	private long number;
	
	public NotFoundCardException(String name, long number) {
		super("The user " + name + "does not have a credit card with number " + number);
		this.number = number;
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

}
