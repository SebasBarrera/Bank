package customExceptions;

@SuppressWarnings("serial")
public class AlreadyPaidException extends Exception {
	
	private long number;
	
	public AlreadyPaidException(long number) {
		super("The card with number " + number + " is already paid");
		setNumber(number);
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
