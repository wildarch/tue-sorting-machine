package states;

import lejos.hardware.Button;
import sorter.Main;
import sorter.Mode;

public class ModeSelectionState extends State {

	@Override
	public State nextState(Main m) {
		m.display.drawModeSelect();
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
