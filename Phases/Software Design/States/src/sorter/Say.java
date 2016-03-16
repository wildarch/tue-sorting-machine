package sorter;

import java.io.File;

import lejos.hardware.Sound;

public class Say {
	static File hello = new File("/sounds/hello.wav");
	static File wtf = new File("/sounds/wtf.wav");
	static File black = new File("/sounds/black.wav");
	static File white = new File("/sounds/white.wav");
	static File done = new File("/sounds/done.wav");
	static File calibrating = new File("/sounds/Calibrating motor.wav");
	static File abortButton = new File("/sounds/Fatal - abort button.wav");
	static File connectionLost = new File("/sounds/Fatal - connection lost.wav");
	static File lowBattery = new File("/sounds/Fatal - low battery.wav");
	static File motorJammed = new File("/sounds/Fatal - motor jammed.wav");
	static File softEx = new File("/sounds/Fatal - software exception.wav");
	static File wrongBasket = new File("/sounds/Fatal - wrong basket.wav");
	static File unkownColor = new File("/sounds/Warning - unkown color.wav");
	/*
	 * TODO:
	 * mode is (fast, safe, incremental)
	 * paused
	 */
	public static void unkownColor(){
		Sound.playSample(unkownColor);
	}
	
	public static void wrongBasket(){
		Sound.playSample(wrongBasket);
	}
	
	public static void softwareException(){
		Sound.playSample(softEx);
	}
	
	public static void motorJammed(){
		Sound.playSample(motorJammed);
	}
	
	public static void batteryLow(){
		Sound.playSample(lowBattery);
	}
	
	public static void connectionLost(){
		Sound.playSample(connectionLost);
	}
	
	public static void abortButton(){
		Sound.playSample(abortButton);
	}
	
	public static void calibrating(){
		Sound.playSample(calibrating);
	}
	
	public static void done(){
		Sound.playSample(done);
	}
	
	public static void wtf(){
		Sound.playSample(wtf);
	}
	
	public static void hello(){
		Sound.playSample(hello);
	}
	
	public static void white(){
		Sound.playSample(white);
	}
	
	public static void black(){
		Sound.playSample(black);
	}
}
