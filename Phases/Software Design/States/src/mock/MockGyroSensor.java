package mock;

import peripherals.GyroSensor;
import peripherals.Orientation;

public class MockGyroSensor implements GyroSensor {
	private float rateChange = 0f;
	private Orientation orient = Orientation.Neutral;

	public float getRateChange() {
		// TODO Auto-generated method stub
		return rateChange;
	}
	
	public void setRateChange(float c){
		rateChange = c;
	}

	public Orientation getOrientation() {
		// TODO Auto-generated method stub
		return orient;
	}
	
	public void setOrientation(Orientation o){
		orient = o;
	}

	public Orientation getOrientation(float angle) {
		// TODO Auto-generated method stub
		return orient;
	}

	public void reset() {
		orient = Orientation.Neutral;
		rateChange = 0f;
	}

}
