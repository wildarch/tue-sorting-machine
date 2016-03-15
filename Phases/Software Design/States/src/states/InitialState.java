package states;
import sorter.Main;


public class InitialState extends State {
	private boolean calibrationStarted = false;

	@Override
	public State nextState(Main m) {
		//calibration
		if(!calibrationStarted){
			calibrationStarted = true;
			m.motor.forward();
			m.gyroSensor.reset();
		}
		else if(m.touchSensor.isPressed()){
			m.motor.stop();
			m.motor.reset();
		}

		//If SP then do transition
		if(m.spButton.isDown()){
			m.setReset(false);
			m.setPaused(false);
			return new ReadColorState();
		}
		
		return this;
	}

}
