package states;
import lejos.robotics.Color;
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
			return new InitialState();
		}
		
		int color = m.colorSensor.readColor();
		//TODO say color
		switch(color){
			//read -> M=L
			case Color.BLACK: 
				m.stats.black++;
				m.timer.start();
				return new MotorLeftState();
				
			//read -> M=R
			case Color.WHITE: 
				m.stats.white++; 
				m.timer.start();
				return new MotorRightState();
				
			//read -> done
			case Color.NONE:
				return new DoneState();
				
			//read -> warn (unknown disc) -> M=R
			default:
				m.stats.unknown++;
				m.timer.start();
				return new WarningState(new UnknownColorWarning(), m, new MotorRightState());
		}
	}
}
