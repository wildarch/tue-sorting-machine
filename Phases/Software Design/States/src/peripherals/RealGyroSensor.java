package peripherals;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;

public class RealGyroSensor implements GyroSensor {
	private EV3GyroSensor gyro;
	private SampleProvider provider;
	private float limitAngle;
	
	private float[] sample = new float[1];
	
	public RealGyroSensor(Port port, float limitAngle){
		gyro = new EV3GyroSensor(port);
		gyro.reset();
		provider = gyro.getRateMode();
		
		this.limitAngle = limitAngle;
	}
	
	public float getRateChange(){
		provider.fetchSample(sample, 0);
		return sample[0];
	}
	
	public Orientation getOrientation(){
		float angle = getRateChange();
		return getOrientation(angle);
	}
	
	public Orientation getOrientation(float angle){
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
	
	public void reset(){
		this.gyro.reset();
	}
}
