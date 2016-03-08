package states;
import sorter.Main;
import lejos.hardware.Button;


public class PausedState extends State {
	boolean released = false;

	@Override
	public State nextState(Main m) {
		if(Button.ENTER.isDown() && released){
			m.paused = false;
			return new ReadColorState();
			
		}
		else if(Button.ENTER.isUp()){
			released = true;
		}
		return this;
	}

}
