package peripherals;

import sorter.Mode;

public class MockMotor implements Motor {
	boolean moving = false;
	boolean stalled = false;

	public void setSpeed(Mode m) {
		// TODO Auto-generated method stub
		
	}

	public void turnLeft() {
		// TODO Auto-generated method stub
		moving = true;
	}

	public void turnRight() {
		// TODO Auto-generated method stub
		moving = true;
	}

	public boolean isMoving() {
		// TODO Auto-generated method stub
		return moving;
	}

	public boolean isStalled() {
		// TODO Auto-generated method stub
		return stalled;
	}
	
	public void setStalled(boolean s){
		stalled = s;
	}

	public void stop() {
		// TODO Auto-generated method stub
		moving = false;
	}

	public void slowForward() {
		// TODO Auto-generated method stub
		moving = true;
	}

	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
