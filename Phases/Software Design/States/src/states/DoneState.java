package states;
import lejos.hardware.Button;
import sorter.Main;

public class DoneState extends State {

	@Override
	public State nextState(Main m) {
		if(Button.DOWN.isDown()){
			return new ReadColorState();
		}
		return this;
	}
	
}
