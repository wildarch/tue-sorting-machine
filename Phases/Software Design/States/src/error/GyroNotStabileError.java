package error;

public class GyroNotStabileError extends FatalError {
	public GyroNotStabileError() {
		super("Gyroscope does not stabilize.");
	}

	@Override
	public void say(){
		//TODO Say.
	}
}
