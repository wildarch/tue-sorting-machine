package states;
import error.DiskNotArrivedError;
import error.LongerThanAvgWarning;
import error.MotorJammedError;
import error.WrongBasketError;
import sorter.Main;
import sorter.Mode;
import sorter.Orientation;

public abstract class MotorState extends State {
	private boolean motorStarted = false;
	private Orientation direction;
	public MotorState(Orientation o){
		direction = o;
	}

	@Override
	public State nextState(Main m) {
		if(!motorStarted){
			motorStarted = true;
			if(direction == Orientation.Right) 
				m.motor.turnRight();
			else 
				m.motor.turnLeft();
		}
		
		//M=F
		if(!m.motor.isMoving()){
			//FAST mode
			if(m.getMode() == Mode.FAST){
				return new ReadColorState();
			}
			//G=(current direction, either L or R) and (INCREMENTAL or SAFE mode).
			else if(m.gyroSensor.getOrientation() == this.direction){
				m.timer.start();
				return new StabilizeState();
			}
		}
		
		if(m.timer.getTimeMS() > m.getTAvg()){
			return new WarningState(new LongerThanAvgWarning(), m, this);
		}

		if ((m.getMode() == Mode.SAFE || m.getMode() == Mode.INCREMENTAL)){
			if (m.timer.getTimeMS() > m.getTDMax()){
				return new AbortState(new DiskNotArrivedError(), m);
			}
			else if (m.motor.isStalled()){
				return new AbortState(new MotorJammedError(), m);
			}
			else if ((direction == Orientation.Right && m.gyroSensor.getOrientation() == Orientation.Left) ||
					(direction == Orientation.Left && m.gyroSensor.getOrientation() == Orientation.Right)){
				return new AbortState(new WrongBasketError(), m);
			}
		}
		
		return this;
	}

}
