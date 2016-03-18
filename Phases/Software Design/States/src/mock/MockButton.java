package mock;

import lejos.hardware.Key;
import lejos.hardware.KeyListener;

public class MockButton implements Key {
	
	boolean isDown = false;

	public int getId() {
		return 0;
	}

	public boolean isDown() {
		return isDown;
	}

	public boolean isUp() {
		return !isDown;
	}
	
	public void setDown(boolean b){
		isDown = b;
	}

	public void waitForPress() {}

	public void waitForPressAndRelease() {}

	public void addKeyListener(KeyListener listener) {}

	public void simulateEvent(int event) {}

	public String getName() {
		return null;
	}

}
