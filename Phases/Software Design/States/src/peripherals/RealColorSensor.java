package peripherals;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;

public class RealColorSensor implements ColorSensor {
	private float[] sample = new float[1];
	private EV3ColorSensor sensor;
	private SampleProvider grayScaleProvider;
	
	public RealColorSensor(Port port){
		sensor = new EV3ColorSensor(port);
		grayScaleProvider = sensor.getRedMode();
	}
	
	public float getGrayScale(){
		grayScaleProvider.fetchSample(sample, 0);
		return sample[0];
	}
	
	public DetectedColor detectColor(float sample){
		return ColorEstimator.getColor(sample);
	}
}