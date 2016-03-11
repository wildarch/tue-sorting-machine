package states;
import lejos.hardware.Button;
import sorter.Main;


public class PausedState extends State {
	boolean released = true;
	
	public PausedState(Main m){
		if(m.spButton.isDown()){
			released = false;
		}
	}

	@Override
	public State run(Main m) {
		if(m.spButton.isDown() && released){
			m.setPaused(false);
			return new ReadColorState();
			
		}
		else if(m.spButton.isUp()){
			released = true;
		}
		return this;
	}

}
