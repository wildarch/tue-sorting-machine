package states;
import sorter.Main;
import sorter.Orientation;

public abstract class MotorState extends State {
	
	private Orientation direction;
	private State nextState;
	
	public MotorState(State s, Orientation o){
		nextState = s;
		direction = o;
	}

	@Override
	public State nextState(Main m) {
		if(!m.motor.isMoving()){
			if(direction == Orientation.Right) 
				m.motor.turnRight();
			else 
				m.motor.turnLeft();
			return nextState;
		}
		return this;
	}

}
