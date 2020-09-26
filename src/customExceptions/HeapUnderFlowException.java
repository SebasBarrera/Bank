package customExceptions;

@SuppressWarnings("serial")
public class HeapUnderFlowException extends Exception {
	
	private int size;
	
	public HeapUnderFlowException(int heapSize) {
		super("The heap size actually is " + heapSize + ", and it need to be at least 1 for do the extract max");
		setSize(heapSize);
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
}
