package peripherals;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.filter.OffsetCorrectionFilter;
import lejos.utility.Delay;

public class RealGyroSensor implements GyroSensor {
	private EV3GyroSensor gyro;
	private SampleProvider provider;
	private OffsetCorrectionFilter filter;
	private float limitAngle;
	
	private float[] sample = new float[1];
	
	public RealGyroSensor(Port port, float limitAngle){
		gyro = new EV3GyroSensor(port);
		gyro.reset();
		provider = gyro.getRateMode();
		filter = new OffsetCorrectionFilter(provider);
		this.limitAngle = limitAngle;
	}
	
	public float getRateChange(){
		filter.fetchSample(sample, 0);
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

	public void calibrate() {
		float[] sample = new float[2];
		SampleProvider angleAndRateProvider = gyro.getAngleAndRateMode();
		angleAndRateProvider.fetchSample(sample, 0);
		getRateChange();
		//Wait 4 seconds until the gyro is stable again
		Delay.msDelay(4000);
		filter = new OffsetCorrectionFilter(provider);
	}
}
