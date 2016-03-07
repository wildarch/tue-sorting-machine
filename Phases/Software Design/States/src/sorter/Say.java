package sorter;

import java.io.File;

import lejos.hardware.Sound;

public class Say {
	static File hello = new File("/sounds/hello.wav");
	static File wtf = new File("/sounds/wtf.wav");
	static File black = new File("/sounds/black.wav");
	static File white = new File("/sounds/white.wav");
	
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
