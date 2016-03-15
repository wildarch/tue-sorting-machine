package error;

public class WrongBasketError extends FatalError {

	public WrongBasketError(float angle) {
		super("Wrong basket, angle: "+angle);
	}

}
