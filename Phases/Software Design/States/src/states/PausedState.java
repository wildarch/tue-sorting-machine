package states;
import sorter.Main;


public class PausedState extends State {
	boolean released = true;
	boolean down = false;
	
	public PausedState(Main m){
		//TODO say paused
		if(m.SPBUTTON.isDown()){
			released = false;
		}
	}

	@Override
	public State nextState(Main m) {
		if(m.isReset()){
			m.variableReset();
			return new ModeSelectionState(m);
		}
		if(m.SPBUTTON.isDown() && released){
			down = true;
		}
		else if (m.SPBUTTON.isUp() && down){
			m.setPaused(false);
			return new ReadColorState();
		}
		else if(m.SPBUTTON.isUp()){
			released = true;
		}
		return this;
	}

}
