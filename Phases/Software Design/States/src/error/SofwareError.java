package error;

import sorter.AbstractMain;
import sorter.Main;
import states.AbortState;

public class SofwareError extends FatalError {

	public SofwareError(AbstractMain abstractMain) {
		super("Software Exception");
		//TODO say software exception
		//m.run();
	}

}
