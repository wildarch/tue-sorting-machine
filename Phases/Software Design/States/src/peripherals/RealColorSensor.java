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
		float avg = 0;
		for (int i = 0; i < 10; i++){
			grayScaleProvider.fetchSample(sample, 0);
			avg = avg * i + sample[0];
			avg/=i+1;
		}

		return avg;
	}
	
	public DetectedColor detectColor(float sample){
		//System.out.println(sample);
		return CloudtEstimator.getColor(sample);
		//return ColorEstimator.getColor(sample);
	}
}