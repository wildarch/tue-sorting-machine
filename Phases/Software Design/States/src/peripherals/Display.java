package peripherals;

import error.FatalError;
import error.Warning;
import lejos.hardware.lcd.Image;
import sorter.Statistics;
import states.State;

public interface Display {
	public void drawCount(State state, Statistics stats);
	
	public void drawModeSelect();
	
	public void drawSuccessChance(float chance);
	
	public void drawFatal(FatalError fatal);
	
	public void drawWarning(Warning warning);
}
