public class saveException extends RuntimeException {
	//Parameterless Constructor
	public saveException() {}

	//Constructor that accepts a message
	public saveException(String message){
		 super(message);
	}
}
