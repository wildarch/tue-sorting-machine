package error;

import sorter.Say;

public class GyroNotStabileError extends FatalError {
	public GyroNotStabileError() {
		super("Gyroscope does not stabilize.");
	}

	@Override
	public void say(){
		Say.gyroNotStab();
	}
}
