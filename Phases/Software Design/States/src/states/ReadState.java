package states;
import lejos.robotics.Color;
import sorter.Main;
import error.UnknownColorWarning;

public class ReadState extends State {
	@Override
	public State run(Main m) {
		if(m.isPaused()){
			m.peripheralReset();
			return new PausedState();
		}
		
		if(m.isReset()){
			m.peripheralReset();
			return new InitialState();
		}

		int color = m.colorSensor.readColor();
		switch(color){
			case Color.BLACK:
				m.stats.black++;
				m.timerStart();
				return new MotorRightState();
				
			case Color.WHITE:
				m.stats.white++; 
				m.timerStart();
				return new MotorLeftState();
				
			case Color.NONE:
				m.peripheralReset();
				return new DoneState();
				
			default:
				m.stats.unknown++;
				m.timerStart();
				return new UnknownWarningState();
		}
	}

}