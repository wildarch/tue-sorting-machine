package mock;

import peripherals.GyroSensor;
import peripherals.Orientation;

public class MockGyroSensor implements GyroSensor {
	private float rateChange = 0f;
	private float limitAngle;
	
	public MockGyroSensor(float l){
		limitAngle = l;
	}

	public float getRateChange() {
		return rateChange;
	}
	
	public void setRateChange(float c){
		rateChange = c;
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

	public Orientation getOrientation() {
		return getOrientation(rateChange);
	}

	public void reset() {
		rateChange = 0f;
	}

	public void calibrate() {}

}
