package error;

import sorter.Say;

public class AbortButtonError extends FatalError {

	public AbortButtonError() {
		super("Abort button pressed");
	}

	@Override
	public void say(){
		Say.abortButton();
	}
}
