package apps;

import peripherals.ColorSensor;
import peripherals.DetectedColor;
import peripherals.GyroSensor;
import peripherals.Motor;
import peripherals.Orientation;
import peripherals.RealColorSensor;
import peripherals.RealGyroSensor;
import peripherals.RealMotor;
import sorter.Main;
import sorter.Timer;

public class TimerApp{
	private ColorSensor colorSensor = new RealColorSensor(Main.COLOR_SENSOR_PORT);
	private Motor motor = new RealMotor(Main.MOTOR_PORT, Main.MOTOR_TURN_STEP, Main.MOTOR_SAFE_SPEED, Main.MOTOR_FAST_SPEED);
	private GyroSensor gyroSensor = new RealGyroSensor(Main.GYRO_SENSOR_PORT, Main.GYRO_STABLE_TRESHOLD);
	private int weight;
	private double average;
	private long min = Long.MAX_VALUE;
	private long max;
	private long gavg;
	private long gweight;
	private long gmin;
	private long gmax;
	
	

	private Timer timer = new Timer();
	
	public void run() throws InterruptedException{
		boolean discPresent = true;
		
		while(discPresent){
			//read color
			float grayScale = colorSensor.getGrayScale();
			DetectedColor color = colorSensor.detectColor(grayScale);
			System.out.println(grayScale);
			switch(color){
				case NONE:
					System.out.println("No disc");
					discPresent = false;
					break;
					
				case BLACK:
					motor.turnLeft();
					break;
					
					default:
						System.out.println("Unknown");
				case WHITE:
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
				timer.start();//measure gyro time
				while(gyroSensor.getOrientation()!= Orientation.Neutral);//wait for gyro stable
				long g = timer.getTimeMS();
				average = (average*weight + t)/(weight+1);
				weight++;
				
				System.out.println("t: "+t);
				
				min = Math.min(min, t);
				max = Math.max(max, t);
				
				gavg = (gavg*gweight + g)/(gweight+1);
				gweight++;
				
				System.out.println("g: "+g);
				
				gmin = Math.min(gmin, g);
				gmax = Math.max(gmax, g);
				
				Thread.sleep(1000);
			}
		}
		
		System.out.println("Average: " + average);
		System.out.println("Min: " + min);
		System.out.println("Max: " + max);
		System.out.println("GAverage: " + gavg);
		System.out.println("GMin: " + gmin);
		System.out.println("GMax: " + gmax);
	}
	
	public static void main(String[] args) throws InterruptedException{
		TimerApp m = new TimerApp();
		m.run();
	}
}
