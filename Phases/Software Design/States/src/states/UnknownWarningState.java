package states;

import sorter.Main;

public class UnknownWarningState extends State {

	@Override
	public State run(Main m) {
		//TODO display warning
		return new MotorRightState();
	}

}