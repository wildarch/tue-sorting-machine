package states;
import sorter.Main;

public class DoneState extends State {
	public DoneState(){
		//TODO say done, show more stats. Flash green LEDs
	}

	@Override
	public State nextState(Main m) {
		if(m.rButton.isDown()){
			m.stats.reset();
			return new ModeSelectionState();
		}
		return this;
	}
	
}
