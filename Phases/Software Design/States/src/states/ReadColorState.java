package states;
import error.UnknownColorWarning;
import peripherals.ColorEstimator;
import peripherals.DetectedColor;
import sorter.AbstractMain;
import sorter.Mode;
import sorter.Say;

public class ReadColorState extends State {
	@Override
	public State nextState(AbstractMain m) {
		//read -> paused
		if(m.isPaused()){
			m.totalTimer.pause();
			return new PausedState(m);
		}
		
		if(m.isReset()){
			m.variableReset();
			return new ModeSelectionState(m);
		}
		
		float grayScale = m.colorSensor.getGrayScale();
		DetectedColor color = m.colorSensor.detectColor(grayScale);
		m.stats.addWrongChance(ColorEstimator.getWrongChance(grayScale, color));
		switch(color){
			//read -> M=L
			case BLACK:
				if(!(m.getMode() == Mode.FAST))
					Say.black();
				m.stats.black++;
				return new MotorLeftState(m);
				
			//read -> M=R
			case WHITE:
				if(!(m.getMode() == Mode.FAST))
					Say.white();
				m.stats.white++;
				return new MotorRightState(m);
				
			//read -> done
			case NONE:
				return new DoneState();
				
			//read -> warn (unknown disc) -> M=R
			default:
				m.stats.unknown++;
				return new WarningState(new UnknownColorWarning(), m, new MotorRightState(m));
		}
	}
}
