package error;

import peripherals.Orientation;
import sorter.Say;

public class WrongBasketError extends FatalError {

	public WrongBasketError(float angle, Orientation direction,Orientation orientation) {
		super("Wrong basket, angle: " + angle + "," + direction + "," + orientation);
		System.out.println("Wrong basket, angle: "+angle + " direction: " + direction + " orientation:" + orientation);
	}

	@Override
	public void say(){
		Say.wrongBasket();
	}
}
