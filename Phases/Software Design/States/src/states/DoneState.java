package states;
import sorter.Main;

public class DoneState extends State {

	@Override
	public State nextState(Main m) {
		if(m.rButton.isDown()){
			m.stats.reset();
			return new ModeSelectionState();
		}
		return this;
	}
	
}
