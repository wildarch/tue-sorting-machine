package mock;

import peripherals.TouchSensor;

public class MockTouchSensor implements TouchSensor {
	private boolean pressed = false;

	public boolean isPressed() {
		// TODO Auto-generated method stub
		return pressed;
	}
	
	public void setPressed(boolean p){
		pressed = p;
	}

}
