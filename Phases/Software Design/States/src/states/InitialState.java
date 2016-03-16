package states;
import sorter.Main;


public class InitialState extends State {
	private boolean calibrationStarted = false;
	private boolean calibrationFinished = false;
	private boolean pressed = false;

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
			this.calibrationFinished = true;
//			m.setPaused(false);
//			m.setReset(false);
//			return new PausedState(m);
		}
		
		
		if(this.calibrationFinished && m.spButton.isDown()){
			this.pressed = true;
		}
		
		if(this.pressed && m.spButton.isUp()){
			m.setPaused(false);
			m.setReset(false);
			return new ReadColorState();
		}
		
		return this;
	}

}
