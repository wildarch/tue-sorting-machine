package peripherals;

import lejos.hardware.Battery;

public class RealBattery implements peripherals.Battery {

	public float getVoltage() {
		return Battery.getVoltage();
	}
	
}
