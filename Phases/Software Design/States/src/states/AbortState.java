package states;

import lejos.hardware.Button;
import sorter.Main;
import error.FatalError;

public class AbortState extends State {
	
	public AbortState(FatalError error, Main m){
		m.motor.stop();
		isAbort = true;
		m.display.drawFatal(error);
	}

	@Override
	public State nextState(Main m) {		
		if(Button.DOWN.isDown()){
			return new PausedState();
		}
		return this;
	}
}
