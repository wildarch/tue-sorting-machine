package peripherals;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3TouchSensor;

public class RealTouchSensor implements TouchSensor {
	EV3TouchSensor sensor;
	
	private float[] sample = new float[1];
	
	public RealTouchSensor(Port port){
		sensor = new EV3TouchSensor(port);
	}
	
	public boolean isPressed(){
		sensor.fetchSample(sample, 0);
		return sample[0] == 1.0f;
	}
}
