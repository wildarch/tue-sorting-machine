package sorter;

import java.io.File;

import lejos.hardware.Sound;

public class Say {
	static File hello = new File("/sounds/hello.wav");
	static File wtf = new File("/sounds/wtf.wav");
	static File black = new File("/sounds/black.wav");
	static File white = new File("/sounds/white.wav");
	static File done = new File("/sounds/Done.wav");
	static File calibrating = new File("/sounds/Calibrating motor.wav");
	static File abortButton = new File("/sounds/Fatal - abort button.wav");
	static File connectionLost = new File("/sounds/Fatal - connection lost.wav");
	static File lowBattery = new File("/sounds/Fatal - low battery.wav");
	static File motorJammed = new File("/sounds/Fatal - motor jammed.wav");
	static File softEx = new File("/sounds/Fatal - software exception.wav");
	static File wrongBasket = new File("/sounds/Fatal - wrong basket.wav");
	static File unkownColor = new File("/sounds/Warning - unkown color.wav");
	static File fastMode = new File("/sounds/Fast mode.wav");
	static File paused = new File("/sounds/Paused.wav");
	static File timeDev = new File("/sounds/Time deviates.wav");
	static File safeMode = new File("/sounds/Safe mode.wav");
	static File stepMode = new File("/sounds/Incremental mode.wav");
	static File discNotArrive = new File("/sounds/Fatal - disc not arrived.wav");
	static File gyroNotStab = new File("/sounds/Gyro not stabilize.wav");
	static File ready = new File("/sounds/Ready.wav");
	static File dumpert = new File("/sounds/Dumpert");
	
	private static class SThread extends Thread{
		private volatile boolean running;
		private File waiting;
		private File current;
		
		@Override
		public void run(){
			running = true;
			
			while(running){
				if(this.current != null){
					try {
						Sound.playSample(this.current, 100);
					}
					catch(NoClassDefFoundError e){
						//For testing, This is OK
					}
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
	
	public static void dumpert(){
		play(dumpert);
	}
	
	public static void ready() {
		play(ready);
	}
	
	public static void fast(){
		play(fastMode);
	}
	
	public static void paused(){
		play(paused);
	}
	
	public static void timeDev(){
		play(timeDev);
	}
	
	public static void safe(){
		play(safeMode);
	}
	
	public static void incremental(){
		play(stepMode);
	}
	
	public static void discNotArrived(){
		play(discNotArrive);
	}
	
	public static void gyroNotStab(){
		play(gyroNotStab);
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
