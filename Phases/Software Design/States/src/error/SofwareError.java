package error;

import sorter.Main;

public class SofwareError extends FatalError {

	public SofwareError(Main m) {
		super("Software Exception");
		//TODO say software exception
		m.run();
	}

}
