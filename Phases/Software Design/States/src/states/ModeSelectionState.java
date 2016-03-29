package states;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import peripherals.ColorEstimator;
import sorter.AbstractMain;
import sorter.Mode;
import sorter.Say;

public class ModeSelectionState extends State {

	public ModeSelectionState(AbstractMain m) {
		m.stats.reset();
	}

	@Override
	public State nextState(AbstractMain m) {
		ColorEstimator.readFromFile();
		while(!m.display.drawModeSelect()) {}
		int button = Button.waitForAnyPress();
		switch(button){
			case Button.ID_LEFT: m.setMode(Mode.FAST); Say.fast(); break;
			case Button.ID_ENTER: m.setMode(Mode.SAFE); Say.safe(); break;
			case Button.ID_RIGHT: m.setMode(Mode.INCREMENTAL); Say.incremental(); break;
			case Button.ID_DOWN: Sound.beepSequence(); System.exit(0); break;
			case Button.ID_UP: Sound.beepSequenceUp(); m.colorSensor.calibrationSequence(m); return this;
		}
		//System.out.println("Mode: "+m.getMode());
		while(m.spButton.isDown()){
			//Wait until the start/pause button is released
		}

		return new InitialState();
	}

}
