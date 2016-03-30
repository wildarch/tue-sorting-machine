package states;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.utility.Delay;
import peripherals.ColorEstimator;
import sorter.AbstractMain;
import sorter.Mode;
import sorter.Say;

public class ModeSelectionState extends State {

	public ModeSelectionState(AbstractMain m) {
		m.variableReset();
	}

	@Override
	public State nextState(AbstractMain m) {
		m.variableReset();
		while(!m.display.drawModeSelect()) {}
		ColorEstimator.readFromFile();
		int button = Button.waitForAnyPress();
		switch(button){
			case Button.ID_LEFT: m.setMode(Mode.FAST); Say.fast(); break;
			case Button.ID_ENTER: m.setMode(Mode.SAFE); Say.safe(); break;
			case Button.ID_RIGHT: m.setMode(Mode.INCREMENTAL); Say.incremental(); break;
			case Button.ID_DOWN: Sound.beepSequence(); System.exit(0); break;
			case Button.ID_UP: Sound.beepSequenceUp(); m.colorSensor.calibrationSequence(m); return this;
			case Button.ID_ESCAPE: m.motor.setSpeed(Mode.FAST); m.motor.rotate(12*120); return this;
		}
		//System.out.println("Mode: "+m.getMode());
		while(m.spButton.isDown()){
			//Wait until the start/pause button is released
		}

		return new InitialState();
	}

}
