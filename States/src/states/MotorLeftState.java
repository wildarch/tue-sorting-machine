package states;

import sorter.Orientation;

public class MotorLeftState extends MotorState {
	public MotorLeftState(){
		super(new GyroLeftState(), Orientation.Left);
	}
}
