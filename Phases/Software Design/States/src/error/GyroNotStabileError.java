package error;

public class GyroNotStabileError extends FatalError {
	public GyroNotStabileError() {
		super("Gyroscope does not stabilize.");
	}

}
