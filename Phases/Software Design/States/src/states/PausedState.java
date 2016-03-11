package states;
import sorter.Main;


public class PausedState extends State {
	boolean released = true;
	boolean down = false;
	
	public PausedState(Main m){
		if(m.spButton.isDown()){
			released = false;
		}
	}

	@Override
	public State run(Main m) {
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
