package error;

import sorter.Say;

public class UnknownColorWarning extends Warning {

	public UnknownColorWarning() {
		super("Unknown colour detected");
	}

	@Override
	public void say() {
		Say.wtf();
	}

}
