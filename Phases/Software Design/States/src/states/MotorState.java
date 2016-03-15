package states;
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
				//TODO timer start
				return new StabilizeState();
			}
		}
		
		if(m.timer.getTimeMS() > m.getTAvg()){
			//TODO to warn
		}

		if((!(m.getMode() == Mode.SAFE || m.getMode() == Mode.INCREMENTAL)) || m.timer.getTimeMS() > m.getTDMax()){
			//TODO to abort
		}
				
		//TODO G=L OR M=J --> to abort
		
		return this;
	}

}
