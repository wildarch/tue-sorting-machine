package states;
import sorter.AbstractMain;
import sorter.Mode;
import sorter.Say;


public class InitialState extends State {
	private boolean calibrationStarted = false;
	private boolean calibrationFinished = false;
	private boolean pressed = false;
	private boolean readySaid = false;

	@Override
	public State nextState(AbstractMain m) {
		//calibration
		if(!calibrationStarted){
			Say.calibrating();
			calibrationStarted = true;
			if(m.getMode() != Mode.FAST){
				m.gyroSensor.calibrate();
			}
			m.motor.slowForward();
		}
		else if(m.touchSensor.isPressed()){
			m.motor.stop();
			m.motor.reset();
			if(!readySaid){
				Say.ready();
				readySaid = true;
			}
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
			m.totalTimer.start();
			return new ReadColorState();
		}
		
		return this;
	}

}
