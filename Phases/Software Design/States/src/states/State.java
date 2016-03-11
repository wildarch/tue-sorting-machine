package states;
import sorter.Main;

public abstract class State {
	public abstract State nextState(Main m);
	
	public void displayUpdate(Main m){
		m.display.drawCount(this, m.stats);
	}
}
