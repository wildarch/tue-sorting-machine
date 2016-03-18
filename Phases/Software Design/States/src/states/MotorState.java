package states;
import peripherals.Orientation;
import sorter.AbstractMain;
import sorter.Mode;
import error.DiskNotArrivedError;
import error.LongerThanAvgWarning;
import error.MotorJammedError;
import error.WrongBasketError;

public abstract class MotorState extends State {
	private boolean motorStarted = false;
	private Orientation direction;
	private boolean hit = false;
	public MotorState(Orientation o){
		direction = o;
	}

	@Override
	public State nextState(AbstractMain m) {
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
			else if(hit){
				m.timer.start();
				return new StabilizeState();
			}
		}
		
		if(m.gyroSensor.getOrientation() == this.direction){
			hit = true;
		}
		
		if(m.timer.getTimeMS() > m.getTAvg()){
			return new WarningState(new LongerThanAvgWarning(), m, this);
		}
		
		if (m.motor.isStalled()){
			return new AbortState(new MotorJammedError(), m);
		}
		
		if ((m.getMode() == Mode.SAFE || m.getMode() == Mode.INCREMENTAL)){
			if (m.timer.getTimeMS() > m.getTDMax()){
				return new AbortState(new DiskNotArrivedError(), m);
			}
			else {
				float angle = m.gyroSensor.getRateChange();
				Orientation orient = m.gyroSensor.getOrientation(angle);
				if(orient != Orientation.Neutral && orient != direction && !hit){
					return new AbortState(new WrongBasketError(angle), m);
				}
			}
		}
		
		return this;
	}

}
