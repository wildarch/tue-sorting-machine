package states;
import lejos.robotics.Color;
import sorter.Main;
import error.UnknownColorWarning;

public class ReadColorState extends State {
	@Override
	public State run(Main m) {
		//read -> paused
		if(m.isPaused()){
			return new PausedState(m);
		}
		
		if(m.isReset()){
			return new InitialState();
		}
		
		int color = m.colorSensor.readColor();
		switch(color){
			//read -> M=L
			case Color.BLACK: 
				m.stats.black++;
				//TODO start timer
				return new MotorLeftState();
				
			//read -> M=R
			case Color.WHITE: 
				m.stats.white++; 
				//TODO start timer
				return new MotorRightState();
				
			//read -> done
			case Color.NONE:
				return new DoneState();
				
			//read -> warn (unknown disc) -> M=R
			default:
				m.stats.unknown++;
				//TODO start timer
				return new WarningState(new UnknownColorWarning(), m, new MotorRightState());
		}
	}
}
