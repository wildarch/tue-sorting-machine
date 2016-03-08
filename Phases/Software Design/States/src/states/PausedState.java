package states;
import lejos.hardware.Button;
import sorter.Main;


public class PausedState extends State {
	boolean released = true;
	
	public PausedState(){
		if(Button.ENTER.isDown()){
			released = false;
		}
	}

	@Override
	public State nextState(Main m) {
		if(Button.ENTER.isDown() && released){
			m.setPaused(false);
			return new ReadColorState();
			
		}
		else if(Button.ENTER.isUp()){
			released = true;
		}
		return this;
	}

}
