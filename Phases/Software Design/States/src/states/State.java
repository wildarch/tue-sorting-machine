package states;
import sorter.Main;

public abstract class State {
	protected boolean isAbort = false;
	
	public abstract State nextState(Main m);
	
	public boolean isAbort(){
		return isAbort;
	}
}
