package peripherals;

import sorter.AbstractMain;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

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
		return ColorEstimator.getColor(sample);
		//return ColorEstimator.getColor(sample);
	}

	public void calibrationSequence(AbstractMain m) {
		float black = m.colorSensor.getGrayScale();
		m.motor.turnLeft();
		Delay.msDelay(3000);
		float white = m.colorSensor.getGrayScale();
		m.motor.turnRight();
		Delay.msDelay(3000);
		float none = m.colorSensor.getGrayScale();
		
		ColorEstimator.blackGS = black;
		ColorEstimator.whiteGS = white;
		ColorEstimator.noneGS = none;
		ColorEstimator.writeToFile(none, black, white);
		ColorEstimator.calibrate();

		LCD.clear();
		//TODO say calibration done
		Sound.beepSequence();
	}
}