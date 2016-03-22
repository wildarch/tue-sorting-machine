package error;

import sorter.Say;

public class DiskNotArrivedError extends FatalError {
	public DiskNotArrivedError() {
		super("Disk did not arrive in the basket.");
	}
	
	public void say(){
		Say.discNotArrived();
	}
}
