package peripherals;

import sorter.AbstractMain;
import sorter.Main;
import lejos.hardware.Sound;
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

	public void calibrationSequence(AbstractMain m) {
		float black = m.colorSensor.getGrayScale();
		m.motor.turnLeft();
		float white = m.colorSensor.getGrayScale();
		m.motor.turnRight();
		float none = m.colorSensor.getGrayScale();
		
		ColorEstimator.blackGS = black;
		ColorEstimator.whiteGS = white;
		ColorEstimator.noneGS = none;
		ColorEstimator.calibrate();
		
		System.out.println("black:"+black);
		System.out.println("white:"+white);
		System.out.println("none:"+none);
		//TODO say calibration done
		Sound.beepSequence();
	}
}