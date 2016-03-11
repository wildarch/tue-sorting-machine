package states;

import sorter.Main;
import error.FatalError;

public class AbortState extends State {
	
	public AbortState(FatalError error, Main m){
		m.motor.stop();
		m.display.drawFatal(error);
	}

	@Override
	public State run(Main m) {		
		if(m.rButton.isDown()){
			m.stats.reset();
			return new PausedState(m);
		}
		return this;
	}
}
