package states;

import sorter.AbstractMain;
import error.Warning;

public class WarningState extends State {
	State nextState;
	
	public WarningState(Warning w, AbstractMain abstractMain, State s){
		//TODO say warning
		abstractMain.display.drawWarning(w);
		nextState = s;
	}

	@Override
	public State nextState(AbstractMain m) {
		return nextState;
	}
}
