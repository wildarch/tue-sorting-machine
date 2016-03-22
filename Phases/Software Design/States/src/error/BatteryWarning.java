package error;

import sorter.Say;

public class BatteryWarning extends Warning {

	public BatteryWarning() {
		super("Battery power too low.");
	}

	@Override
	public void say() {
		Say.batteryLow();		
	}

}
