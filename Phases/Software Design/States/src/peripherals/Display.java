package peripherals;

import sorter.Statistics;
import states.State;
import error.FatalError;
import error.Warning;

public interface Display {
	public void drawCount(State state, Statistics stats);
	
	public boolean drawModeSelect();
	
	public void drawSuccessChance(float chance);
	
	public void drawFatal(FatalError fatal);
	
	public void drawWarning(Warning warning);
	
	public void drawTime(long t);

	public void setDisplayReadyTime(long t);

	public void refresh();

	public void drawCount(State state, Statistics stats, boolean b);
}
