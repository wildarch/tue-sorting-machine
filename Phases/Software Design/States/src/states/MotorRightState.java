package states;

import peripherals.Orientation;
import sorter.AbstractMain;
import sorter.Main;

public class MotorRightState extends MotorState {
	public MotorRightState(AbstractMain m) {
		super(Orientation.Right, m);
	}
}
