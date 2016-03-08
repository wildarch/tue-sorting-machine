package states;

import lejos.hardware.Button;
import sorter.Main;
import error.FatalError;

public class AbortState extends State {
	
	public AbortState(FatalError error, Main m){
		isAbort = true;
		m.display.drawFatal(error);
	}

	@Override
	public State nextState(Main m) {		
		if(Button.DOWN.isDown()){
			m.motor.stop();
			return new PausedState();
		}
		return this;
	}
}