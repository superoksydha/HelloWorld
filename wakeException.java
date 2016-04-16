public class wakeException extends RuntimeException {
	//Parameterless Constructor
	public wakeException() {}

	//Constructor that accepts a message
	public wakeException(String message){
		 super(message);
	}
}
