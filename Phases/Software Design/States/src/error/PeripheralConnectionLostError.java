package error;

public class PeripheralConnectionLostError extends FatalError {

	public PeripheralConnectionLostError() {
		super("Connection to a peripheral lost.");
	}
}
