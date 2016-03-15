package states;
import lejos.robotics.Color;
import sorter.ColorEstimator;
import sorter.DetectedColor;
import sorter.Main;
import error.UnknownColorWarning;

public class ReadColorState extends State {
	@Override
	public State nextState(Main m) {
		//read -> paused
		if(m.isPaused()){
			return new PausedState(m);
		}
		
		if(m.isReset()){
			return new ModeSelectionState();
		}
		
		float grayScale = m.colorSensor.getGrayScale();
		DetectedColor color = m.colorSensor.detectColor(grayScale);
		m.stats.addWrongChance(ColorEstimator.getWrongChance(grayScale, color));
		//TODO say color
		switch(color){
			//read -> M=L
			case BLACK: 
				m.stats.black++;
				//TODO start timer
				return new MotorLeftState();
				
			//read -> M=R
			case WHITE: 
				m.stats.white++; 
				//TODO start timer
				return new MotorRightState();
				
			//read -> done
			case NONE:
				return new DoneState();
				
			//read -> warn (unknown disc) -> M=R
			default:
				m.stats.unknown++;
				//TODO start timer
				return new WarningState(new UnknownColorWarning(), m, new MotorRightState());
		}
	}
}
