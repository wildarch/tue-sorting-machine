package error;

public abstract class FatalError extends Error {

	public FatalError(String m) {
		super(m);
	}

	public abstract void say();
}
