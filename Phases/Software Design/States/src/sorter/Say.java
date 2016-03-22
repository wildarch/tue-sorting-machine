package sorter;

import java.io.File;

import lejos.hardware.Sound;

public class Say {
	private static File hello = new File("/sounds/hello.wav");
	private static File wtf = new File("/sounds/wtf.wav");
	private static File black = new File("/sounds/black.wav");
	private static File white = new File("/sounds/white.wav");
	private static File done = new File("/sounds/done.wav");
	private static File calibrating = new File("/sounds/Calibrating motor.wav");
	private static File abortButton = new File("/sounds/Fatal - abort button.wav");
	private static File connectionLost = new File("/sounds/Fatal - connection lost.wav");
	private static File lowBattery = new File("/sounds/Fatal - low battery.wav");
	private static File motorJammed = new File("/sounds/Fatal - motor jammed.wav");
	private static File softEx = new File("/sounds/Fatal - software exception.wav");
	private static File wrongBasket = new File("/sounds/Fatal - wrong basket.wav");
	private static File unkownColor = new File("/sounds/Warning - unkown color.wav");
	private static File fastMode = new File("/sounds/fast mode.wav");
	private static File safeMode = new File("/sounds/safe mode.wav");
	private static File incrementalMode = new File("/sounds/incremental mode.wav");
	private static File paused = new File("/sounds/paused.wav");
	
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
			if(this.current == null){
				this.current = sound;
			}
			else{
				this.waiting = sound;
			}
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
	
	private static void stop(){
		thread.requestStop();
	}
	
	public static void waitForStop() throws InterruptedException{
		stop();
		thread.join();
	}
	
	public static void paused(){
		play(paused);
	}
	
	public static void fastMode(){
		play(fastMode);
	}
	
	public static void safeMode(){
		play(safeMode);
	}
	
	public static void incrementalMode(){
		play(incrementalMode);
	}
	
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
