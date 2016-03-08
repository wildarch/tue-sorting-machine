package states;
import lejos.hardware.Button;
import sorter.Main;


public class PausedState extends State {
	boolean released = false;

	@Override
	public State run(Main m) {
		if(m.isReset()){
			return new InitialState();
		}
		
		if(Button.ENTER.isDown() && released){
			m.setPaused(false);
			m.peripheralStart();
			return new ReadState();
			
		}
		else if(Button.ENTER.isUp()){
			released = true;
		}
		return this;
	}

}
