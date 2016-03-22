package states;

import sorter.AbstractMain;
import error.FatalError;

public class AbortState extends State {
	
	FatalError error;
	
	public AbortState(FatalError e, AbstractMain abstractMain){
		e.say();
		abstractMain.motor.stop();
		error = e;
	}

	@Override
	public State nextState(AbstractMain m) {		
		if(m.rButton.isDown()){
			m.variableReset();
			return new ModeSelectionState(m);
		}
		return this;
	}
	
	@Override
	public void displayUpdate(AbstractMain m){
		m.display.drawFatal(error);
	}
}