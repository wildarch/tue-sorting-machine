package states;
import sorter.AbstractMain;


public class PausedState extends State {
	boolean released = true;
	boolean down = false;
	
	public PausedState(AbstractMain m){
		//TODO say paused
		if(m.spButton.isDown()){
			released = false;
		}
	}

	@Override
	public State nextState(AbstractMain m) {
		if(m.isReset()){
			m.variableReset();
			return new ModeSelectionState(m);
		}
		if(m.spButton.isDown() && released){
			down = true;
		}
		else if (m.spButton.isUp() && down){
			m.setPaused(false);
			return new ReadColorState();
		}
		else if(m.spButton.isUp()){
			released = true;
		}
		return this;
	}

}
