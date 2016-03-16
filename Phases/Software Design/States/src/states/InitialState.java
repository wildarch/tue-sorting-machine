package states;
import sorter.Main;


public class InitialState extends State {
	private boolean calibrationStarted = false;
	private boolean pressed = false;

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
//			m.setPaused(false);
//			m.setReset(false);
//			return new PausedState(m);
		}
		
		
		if(m.spButton.isDown()){
			this.pressed = false;
		}
		
		if(this.pressed && m.spButton.isUp()){
			m.setPaused(false);
			m.setReset(false);
			return new PausedState(m);
		}
		
		return this;
	}

}
