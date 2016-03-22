package states;

import peripherals.Orientation;
import sorter.AbstractMain;
import sorter.Mode;
import sorter.Timer;
import error.GyroNotStabileError;

public class StabilizeState extends State {
	private int MIN_TIMER = 1000;
	private Timer wait = new Timer();
	
	public StabilizeState(){
		wait.start();
	}
	
	@Override
	public State nextState(AbstractMain m) {
		if(m.gyroSensor.getOrientation() == Orientation.Neutral && wait.getTimeMS() > MIN_TIMER){
			if(m.getMode() == Mode.INCREMENTAL){
				return new PausedState(m);
			}
			else{
				return new ReadColorState();
			}
		}

		if(m.timer.getTimeMS() > m.getTGMax()){
			return new AbortState(new GyroNotStabileError(), m);
		}
		
		return this;
	}

}
