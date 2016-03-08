package states;
import lejos.hardware.Sound;
import sorter.Main;


public class InitialState extends State {

	@Override
	public State nextState(Main m) {
		Sound.beep();
		return new PausedState();
	}

}
