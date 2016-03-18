package error;

import sorter.AbstractMain;
import sorter.Main;
import sorter.Say;
import states.AbortState;

public class SofwareError extends FatalError {

	public SofwareError(AbstractMain abstractMain) {
		super("Software Exception");
		Say.softwareException();
	}

}
