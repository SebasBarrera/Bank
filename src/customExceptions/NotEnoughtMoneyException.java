package customExceptions;

@SuppressWarnings("serial")
public class NotEnoughtMoneyException extends Exception {
	
	private double aA;
	private double more;
	private String name;
	private long aC; 

	public NotEnoughtMoneyException(double aA, double more, String name, long aC) {
		super("The user " + name + " with account number " + aC + ", have not enought money to do this withdrawal \n"
				+ "The account have " + aA + "$, and it's trying to withdrawals " + more + "$");
		this.aA = aA;
		this.more = more;
		this.name = name;
		this.aC = aC;
	}

	/**
	 * @return the aA
	 */
	public double getaA() {
		return aA;
	}

	/**
	 * @param aA the aA to set
	 */
	public void setaA(double aA) {
		this.aA = aA;
	}

	/**
	 * @return the more
	 */
	public double getMore() {
		return more;
	}

	/**
	 * @param more the more to set
	 */
	public void setMore(double more) {
		this.more = more;
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
