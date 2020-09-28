package customExceptions;

@SuppressWarnings("serial")
public class PriorityRowIsEmptyException extends Exception {
	
	public PriorityRowIsEmptyException() {
		super("There is no more persons to attend in the priority Queue");
	}


}
