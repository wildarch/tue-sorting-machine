package apps;

import error.UnknownColorWarning;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import sorter.ColorEstimator;
import sorter.ColorSensor;
import sorter.DetectedColor;
import sorter.GyroSensor;
import sorter.Main;
import sorter.Motor;
import sorter.Orientation;
import sorter.Timer;
import states.DoneState;
import states.MotorLeftState;
import states.MotorRightState;
import states.WarningState;

public class TimerApp{
	private ColorSensor colorSensor = new ColorSensor(Main.COLOR_SENSOR_PORT);
	private Motor motor = new Motor(Main.MOTOR_PORT, Main.MOTOR_TURN_STEP, Main.MOTOR_SAFE_SPEED, Main.MOTOR_FAST_SPEED);
	private GyroSensor gyroSensor = new GyroSensor(Main.GYRO_SENSOR_PORT, Main.GYRO_STABLE_TRESHOLD);
	private int weight;
	private double average;

	private Timer timer = new Timer();
	
	public void run() throws InterruptedException{
		boolean discPresent = true;
		
		while(discPresent){
			//read color
			float grayScale = colorSensor.getGrayScale();
			DetectedColor color = colorSensor.detectColor(grayScale);
			switch(color){				
				case NONE:
					discPresent = false;
					break;
					
				case BLACK:
					motor.turnLeft();
					break;
					
				case WHITE:
				default:
					motor.turnRight();
					break;
			}
			
			if(discPresent){
				timer.start();
				boolean touchedGyro = false;
				
				//wait for gyro
				while(!touchedGyro){
					switch(color){
						case BLACK:
							if(gyroSensor.getOrientation() == Orientation.Left){
								touchedGyro = true;
							}
							break;
					
						case WHITE:
						default:
							if(gyroSensor.getOrientation() == Orientation.Right){
								touchedGyro = true;
							}
							break;
					}
				}
				
				long t = timer.getTimeMS();
				
				average = (average*weight + t)/(weight+1);
				weight++;
				
				System.out.println("t: "+t);
				
				Thread.sleep(1000);
			}
		}
		
		System.out.format("Average: %d", average);
	}
	
	public static void main(String[] args) throws InterruptedException{
		TimerApp m = new TimerApp();
		m.run();
	}
}
