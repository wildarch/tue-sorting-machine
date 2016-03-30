package states;
import error.MotorJammedError;
import lejos.hardware.Sound;
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
		else if(m.motor.isStalled()) {
			return new AbortState(new MotorJammedError(m), m);
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
		}
		
		
		if(this.calibrationFinished && m.spButton.isDown()){
			this.pressed = true;
		}
		
		if(m.isReset() && this.calibrationFinished){
			m.variableReset();
			return new ModeSelectionState(m);
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
