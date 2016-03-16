package states;
import sorter.Main;


public class InitialState extends State {
	private boolean calibrationStarted = false;

	@Override
	public State nextState(Main m) {
		//calibration
		if(!calibrationStarted){
			calibrationStarted = true;
			m.motor.slowForward();
			m.gyroSensor.reset();
		}
		else if(m.touchSensor.isPressed()){
			m.motor.stop();
			m.motor.reset();
			m.motor.setSpeed(m.getMode());
			m.setPaused(false);
			m.setReset(false);
			return new PausedState(m);
		}
		
		return this;
	}

}
