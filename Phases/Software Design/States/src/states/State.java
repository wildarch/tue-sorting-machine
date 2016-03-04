package states;
import sorter.Main;

public abstract class State {
	public abstract State nextState(Main m);
}
