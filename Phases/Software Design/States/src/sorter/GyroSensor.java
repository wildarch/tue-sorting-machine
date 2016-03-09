package sorter;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;

public class GyroSensor {
	private EV3GyroSensor gyro;
	private SampleProvider sampler;
	private float limitAngle;
	
	private float[] sample = new float[1];
	
	public GyroSensor(Port port, float limitAngle){
		gyro = new EV3GyroSensor(port);
		gyro.reset();
		sampler = gyro.getAngleMode();
		
		if(sampler == null) {
			//TODO: proper error handling
			LCD.clear();
			LCD.drawString("Gyroscope sampler was null", 0, 0);
		}
		
		this.limitAngle = limitAngle;
	}
	
	public float getAngle(){
		sampler.fetchSample(sample, 0);
		//gyro.fetchSample(sample, 0);
		return sample[0];
	}
	
	public Orientation getOrientation(){
		float angle = getAngle();
		if(angle > limitAngle){
			return Orientation.Right;
		}
		else if(angle  < -limitAngle){
			return Orientation.Left;
		}
		else {
			return Orientation.Neutral;
		}
	}
}
