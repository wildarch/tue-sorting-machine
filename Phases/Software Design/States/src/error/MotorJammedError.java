package error;

import sorter.Say;

public class MotorJammedError extends FatalError {

	public MotorJammedError() {
		super("Motor jammed");
	}
	
	@Override
	public void say(){
		Say.motorJammed();
	}

}
