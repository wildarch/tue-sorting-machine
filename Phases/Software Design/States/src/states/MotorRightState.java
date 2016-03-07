package states;

import sorter.Orientation;

public class MotorRightState extends MotorState {
	public MotorRightState() {
		super(new GyroRightState(), Orientation.Right);
	}
}
