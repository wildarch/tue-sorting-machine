package states;

import peripherals.Orientation;
import sorter.AbstractMain;
import sorter.Mode;
import error.GyroNotStabileError;

public class StabilizeState extends State {

	@Override
	public State nextState(AbstractMain m) {
		if(m.gyroSensor.getOrientation() == Orientation.Neutral){
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
