package states;

import sorter.Main;
import error.FatalError;

public class AbortState extends State {
	
	FatalError error;
	
	public AbortState(FatalError e, Main m){
		//TODO say abort
		m.motor.stop();
		error = e;
	}

	@Override
	public State nextState(Main m) {		
		if(m.rButton.isDown()){
			m.variableReset();
			return new PausedState(m);
		}
		return this;
	}
	
	@Override
	public void displayUpdate(Main m){
		m.display.drawFatal(error);
	}
}