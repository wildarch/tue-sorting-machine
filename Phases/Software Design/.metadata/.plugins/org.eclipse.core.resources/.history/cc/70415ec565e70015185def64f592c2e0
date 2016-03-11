package states;
import lejos.hardware.Button;
import sorter.Main;

public class DoneState extends State {

	@Override
	public State run(Main m) {
		if(m.rButton.isDown()){
			m.stats.reset();
			return new InitialState();
		}
		return this;
	}
	
}
