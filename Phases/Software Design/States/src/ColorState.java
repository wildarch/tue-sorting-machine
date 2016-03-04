public abstract class ColorState extends State {
	
	private int motorRotation = 0;
	private State nextState;
	private boolean started = false;
	
	public ColorState(int r, State s){
		motorRotation = r;
		nextState = s;
	}

	@Override
	public void run(Main m) {
		if(!m.motor.isMoving() && !started){
			m.motor.rotate(motorRotation, true);
			started = true;
		}
		else if(started && !m.motor.isMoving()) {
			m.currentState = nextState;
		}
	}

}
