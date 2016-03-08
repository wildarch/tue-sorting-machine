package states;

import error.AbortButtonError;
import error.FatalError;
import sorter.Main;
import sorter.Mode;
import sorter.Orientation;

public class StabilizeState extends State{

	@Override
	public State run(Main m) {
		if(m.gyroSensor.getOrientation()==Orientation.Neutral){
			if(m.getMode() == Mode.SAFE){
				return new ReadState();
			}
			else{
				return new PausedState();
			}
		}
		
		if(m.timerLargerThanGMax()){
			return new AbortState(new FatalError("Gyro not stable."){}, m);
		}
		
		return this;
	}

}
