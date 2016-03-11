package error;

public abstract class Error {
	private String message;
	
	public Error(String m){
		message = m;
	}
	
	public String getMessage(){
		return message;
	}
}
