package mock;

import peripherals.Motor;
import sorter.Mode;

public class MockMotor implements Motor {
	boolean moving = false;
	boolean stalled = false;

	public void setSpeed(Mode m) {}

	public void turnLeft() {
		moving = true;
	}

	public void turnRight() {
		moving = true;
	}

	public boolean isMoving() {
		return moving;
	}

	public boolean isStalled() {
		return stalled;
	}
	
	public void setStalled(boolean s){
		stalled = s;
	}

	public void stop() {
		moving = false;
	}

	public void slowForward() {
		moving = true;
	}

	public void reset() {}

}
