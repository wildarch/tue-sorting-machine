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
	
	private static class SThread extends Thread{
		private volatile boolean running;
		private File waiting;
		private File current;
		
		public void run(){
			running = true;
			
			while(running){
				if(this.current != null){
					Sound.playSample(this.current);
				}
				
				finished();
			}
		}
		
		public synchronized void play(File sound){
			this.waiting = sound;
		}
		
		private synchronized void finished(){
			this.current = this.waiting;
			this.waiting = null;
		}
		
		public void requestStop(){
			this.running = false;
		}
		
		public boolean isRunning(){
			return this.running;
		}
	}
	
	private static SThread thread = new SThread();
	
	private static void play(File sound){
		if(!thread.isRunning()){
			thread.start();
		}
		
		thread.play(new File(sound.getAbsolutePath()));
	}
	
	/*
	 * TODO:
	 * mode is (fast, safe, incremental)
	 * paused
	 */
	public static void unkownColor(){
		play(unkownColor);
	}
	
	public static void wrongBasket(){
		play(wrongBasket);
	}
	
	public static void softwareException(){
		play(softEx);
	}
	
	public static void motorJammed(){
		play(motorJammed);
	}
	
	public static void batteryLow(){
		play(lowBattery);
	}
	
	public static void connectionLost(){
		play(connectionLost);
	}
	
	public static void abortButton(){
		play(abortButton);
	}
	
	public static void calibrating(){
		play(calibrating);
	}
	
	public static void done(){
		play(done);
	}
	
	public static void wtf(){
		play(wtf);
	}
	
	public static void hello(){
		play(hello);
	}
	
	public static void white(){
		play(white);
	}
	
	public static void black(){
		play(black);
	}
}
