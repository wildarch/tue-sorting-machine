package sorter;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;

public class GyroSensor {
	private EV3GyroSensor gyro;
	private float limitAngle;
	
	private float[] sample = new float[1];
	
	public GyroSensor(Port port, float limitAngle){
		gyro = new EV3GyroSensor(port);
		gyro.reset();
		
		this.limitAngle = limitAngle;
	}
	
	public float getAngle(){
		gyro.fetchSample(sample, 0);
		return sample[0];
	}
	
	public GyroState getState(){
		float angle = getAngle();
		if(angle > limitAngle){
			return GyroState.Right;
		}
		else if(angle < -limitAngle){
			return GyroState.Left;
		}
		else {
			return GyroState.Stable;
		}
	}
}
