package states;
import lejos.hardware.Sound;
import sorter.Main;


public class InitialState extends State {

	@Override
	public State nextState(Main m) {
		if(!m.motor.isMoving()){
			m.motor.forward();
		}
		else if(m.touchSensor.isPressed()){
			m.motor.stop();
			m.motor.reset();
			return new PausedState();
		}
		return this;
	}

}
