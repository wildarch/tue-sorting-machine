package peripherals;

import sorter.Mode;

public interface Motor {
	public void setSpeed(Mode m);
	
	public void turnLeft();
	
	public void turnRight();
	
	public boolean isMoving();
	
	public boolean isStalled();
	
	public void stop();
	
	public void slowForward();
	
	public void reset();
}
