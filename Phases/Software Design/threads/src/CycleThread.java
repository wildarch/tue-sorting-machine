import java.util.concurrent.ArrayBlockingQueue;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.robotics.Color;

public class CycleThread extends Thread {
	public ArrayBlockingQueue<Messages> queue = new ArrayBlockingQueue<>(1024);
	
	private EV3ColorSensor colorSensor =  new EV3ColorSensor(SensorPort.S1);
	
	boolean paused = false;
	boolean abort = false;
	boolean reset = false;
	
	public void run(){
		while(true){			
			float[] sample = new float[1];
			colorSensor.fetchSample(sample, 0);
			int c = (int) sample[0];
			
			Messages msg = null;
			try {
				while ((msg = queue.take()) == null) {
					switch(msg){
					case SP:
						paused = true;
						break;
					case A:
						abort = true;
						break;
					case R:
						reset = true;
						break;
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (!abort && !paused && !reset){
				if(c == Color.BLACK) {
					Motor.A.rotate(40);
					LCD.drawString("Black", 0, 0);
				} else if (c == Color.WHITE) {
					Motor.A.rotate(-40);
					LCD.drawString("White", 0, 0);
				}
				else {
					//Unknown color
					LCD.drawString("Unknown", 0, 0);
					Motor.A.rotate(40);
				}
			}
		}
	}
}
