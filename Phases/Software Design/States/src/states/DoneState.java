package states;
import lejos.hardware.Button;
import sorter.Main;

public class DoneState extends State {

	@Override
	public State run(Main m) {
		if(Button.DOWN.isDown()){
			m.setReset(false);
			return new ReadState();
		}
		return this;
	}
	
}
