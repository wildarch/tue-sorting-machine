package error;

import sorter.AbstractMain;
import sorter.Say;

public class SofwareError extends FatalError {

	public SofwareError(AbstractMain abstractMain) {
		super("Software Exception");
	}
	
	@Override
	public void say() {
		Say.softwareException();
	}

}
