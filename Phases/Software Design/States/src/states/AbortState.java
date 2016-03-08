package states;

import lejos.hardware.Button;
import sorter.Main;
import error.FatalError;

public class AbortState extends State {
	
	public AbortState(FatalError error, Main m){
		m.display.drawFatal(error);
	}

	@Override
	public State run(Main m) {		
		if(Button.DOWN.isDown()){
			m.motor.stop();
			m.setReset(false);
			return new PausedState();
		}
		return this;
	}
}
