package states;

import peripherals.Orientation;
import sorter.AbstractMain;
import sorter.Main;

public class MotorLeftState extends MotorState {
	public MotorLeftState(AbstractMain m){
		super(Orientation.Left, m);
	}
}
