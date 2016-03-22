package error;

public class UnknownColorWarning extends Warning {

	public UnknownColorWarning() {
		super("Unknown colour detected");
	}

	@Override
	public void say() {
	}

}
