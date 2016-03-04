package states;
import sorter.Main;

public abstract class ColorState extends State {
	
	private boolean turnRight;
	private State nextState;
	private boolean started = false;
	
	public ColorState(State s, boolean turnRight){
		nextState = s;
		this.turnRight = turnRight;
	}

	@Override
	public State nextState(Main m) {
		if(!m.motor.isMoving() && !started){
			if(turnRight) 
				m.motor.turnRight();
			else 
				m.motor.turnLeft();
			started = true;
		}
		else if(started && !m.motor.isMoving()) {
			return nextState;
		}
		return this;
	}

}
