package states;

import sorter.Main;
import error.Warning;

public class WarningState extends State {
	State nextState;
	
	public WarningState(Warning w, Main m, State s){
		m.display.drawWarning(w);
		nextState = s;
	}

	@Override
	public State nextState(Main m) {
		return nextState;
	}
}
