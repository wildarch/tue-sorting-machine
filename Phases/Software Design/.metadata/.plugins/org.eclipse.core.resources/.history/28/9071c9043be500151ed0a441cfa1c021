package states;
import lejos.hardware.Sound;
import sorter.Main;


public class InitialState extends State {

	@Override
	public State run(Main m) {
		if(m.startPausedPressed()){
			m.setReset(false);
			m.peripheralStart();
			return new ReadState();
		}
		
		return this;
	}

}
