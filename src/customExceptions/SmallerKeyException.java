package customExceptions;

@SuppressWarnings("serial")
public class SmallerKeyException extends Exception {
	
	/**
	 * @param size the id to set
	 */
	private int k;

	public SmallerKeyException(int key) {
		super("The new key " + key + " is smaller than current key.");
		setKey(key);
	}

	/**
	 * @return the k
	 */
	public int getKey() {
		return k;
	}

	/**
	 * @param k the k to set
	 */
	public void setKey(int k) {
		this.k = k;
	}
		

}

