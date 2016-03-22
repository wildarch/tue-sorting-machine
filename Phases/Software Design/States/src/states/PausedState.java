package states;
import sorter.AbstractMain;
import sorter.Mode;
import sorter.Say;


public class PausedState extends State {
	boolean released = true;
	boolean down = false;
	
	public PausedState(AbstractMain m){
		if(m.getMode() != Mode.INCREMENTAL)
			Say.paused();
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
			m.totalTimer.start();
			return new ReadColorState();
		}
		else if(m.spButton.isUp()){
			released = true;
		}
		return this;
	}

}
