package mock;

import lejos.hardware.Key;
import lejos.hardware.KeyListener;

public class MockButton implements Key {
	
	boolean isDown = false;

	public int getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isDown() {
		// TODO Auto-generated method stub
		return isDown;
	}

	public boolean isUp() {
		// TODO Auto-generated method stub
		return !isDown;
	}
	
	public void setDown(boolean b){
		isDown = b;
	}

	public void waitForPress() {
		// TODO Auto-generated method stub
		
	}

	public void waitForPressAndRelease() {
		// TODO Auto-generated method stub
		
	}

	public void addKeyListener(KeyListener listener) {
		// TODO Auto-generated method stub
		
	}

	public void simulateEvent(int event) {
		// TODO Auto-generated method stub
		
	}

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
