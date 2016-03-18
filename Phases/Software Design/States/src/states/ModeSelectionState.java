package states;

import lejos.hardware.Button;
import sorter.AbstractMain;
import sorter.Mode;

public class ModeSelectionState extends State {

	public ModeSelectionState(AbstractMain m) {
		m.stats.reset();
	}

	@Override
	public State nextState(AbstractMain m) {
		while(!m.display.drawModeSelect()) {}
		int button = Button.waitForAnyPress();
		switch(button){
			case Button.ID_LEFT: m.setMode(Mode.FAST); break;
			case Button.ID_ENTER: m.setMode(Mode.SAFE); break;
			case Button.ID_RIGHT: m.setMode(Mode.INCREMENTAL); break;
		}
		//System.out.println("Mode: "+m.getMode());
		while(m.spButton.isDown()){
			//Wait until the start/pause button is released
		}
		//TODO say mode is ...
		return new InitialState();
	}

}
