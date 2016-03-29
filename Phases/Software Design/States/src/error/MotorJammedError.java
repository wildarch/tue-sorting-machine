package error;

import sorter.AbstractMain;
import sorter.Say;

public class MotorJammedError extends FatalError {

	public MotorJammedError(AbstractMain m) {
		super("Motor jammed");
		m.motor.flt();
	}
	
	@Override
	public void say(){
		Say.motorJammed();
	}

}
