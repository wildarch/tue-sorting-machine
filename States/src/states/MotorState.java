package states;
import sorter.Main;
import sorter.Mode;
import sorter.Orientation;

public abstract class MotorState extends State {
	private boolean motorStarted = false;
	private Orientation direction;
	private State nextState;
	
	public MotorState(State s, Orientation o){
		nextState = s;
		direction = o;
	}

	@Override
	public State run(Main m) {
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
		
		//TODO t>tavg --> to warn
		//TODO SAFE, INC => t>tdmax OR G=L OR M=J --> to abort
		
		return this;
	}

}
