package customExceptions;

@SuppressWarnings("serial")
public class NormalRowIsEmptyException extends Exception {
	
	public NormalRowIsEmptyException() {
		super("There is no more persons to attend in the normal Queue");
	}


}
