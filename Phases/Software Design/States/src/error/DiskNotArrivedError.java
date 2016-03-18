package error;

public class DiskNotArrivedError extends FatalError {
	public DiskNotArrivedError() {
		super("Disk did not arrive in the basket.");
	}
	
	public void say(){
		//TODO Say.
	}
}
