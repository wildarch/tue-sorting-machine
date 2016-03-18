package mock;

import peripherals.Battery;

public class MockBattery implements Battery {
	float voltage = 9f;

	public float getVoltage() {
		// TODO Auto-generated method stub
		return voltage;
	}
	
	public void setVoltage(float v){
		voltage = v;
	}

}
