package states;
import sorter.Main;
import lejos.hardware.Button;

public class DoneState extends State {

	@Override
	public State nextState(Main m) {
		if(Button.DOWN.isDown()){
			return new ReadColorState();
		}
		return this;
	}
	
}
