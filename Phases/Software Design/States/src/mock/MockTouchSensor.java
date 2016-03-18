package mock;

import peripherals.TouchSensor;

public class MockTouchSensor implements TouchSensor {
	private boolean pressed = false;

	public boolean isPressed() {
		return pressed;
	}
	
	public void setPressed(boolean p){
		pressed = p;
	}

}
