package apps;

import lejos.hardware.Button;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import lejos.hardware.Sound;
import sorter.Timer;

public class ButtonApp implements KeyListener {

	Timer t = new Timer();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Sound.beep();
		Button.ENTER.addKeyListener(new ButtonApp());
		while(true){
			if(Button.ESCAPE.isDown()){
				break;
			}
		}
	}

	public void keyPressed(Key k) {
		t.start();
	}

	public void keyReleased(Key k) {
		System.out.println(t.getTimeMS());
	}

}
