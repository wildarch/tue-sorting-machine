package sorter;

import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

public class ColorSensor {
	private float[] sample = new float[1];
	private EV3ColorSensor sensor;
	
	public ColorSensor(Port port){
		sensor = new EV3ColorSensor(port);
	}
	
	public int readColor(){
		sensor.fetchSample(sample, 0);
		return (int) sample[0];
	}
}