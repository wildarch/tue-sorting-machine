package states;

import peripherals.Orientation;
import sorter.AbstractMain;

public class MotorRightState extends MotorState {
	public MotorRightState(AbstractMain m) {
		super(Orientation.Right, m);
	}
}
