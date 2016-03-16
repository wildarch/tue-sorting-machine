package states;

import error.GyroNotStabileError;
import lejos.utility.Delay;
import sorter.Main;
import sorter.Mode;
import sorter.Orientation;

public class StabilizeState extends State {

	@Override
	public State nextState(Main m) {
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
