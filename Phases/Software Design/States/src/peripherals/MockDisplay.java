package peripherals;

import sorter.Statistics;
import states.State;
import error.FatalError;
import error.Warning;

public class MockDisplay implements Display {

	public void drawCount(State state, Statistics stats) {
		System.out.println("In state: "+state.getClass().getSimpleName());
		
	}

	public void drawModeSelect() {
		System.out.println("Selecting mode");
		
	}

	public void drawSuccessChance(float chance) {
		System.out.println("Succes change: "+chance);
		
	}

	public void drawFatal(FatalError fatal) {
		System.out.println("Fatal error!\n"+fatal.getMessage());
		
	}

	public void drawWarning(Warning warning) {
		System.out.println("Warning!\n"+warning.getMessage());
		
	}

}
