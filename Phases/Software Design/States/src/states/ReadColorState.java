package states;
import lejos.robotics.Color;
import sorter.Main;
import error.UnknownColorWarning;

public class ReadColorState extends State {

	@Override
	public State nextState(Main m) {
		if(m.paused){
			return new PausedState();
		}

		int color = m.colorSensor.readColor();
		switch(color){
		case Color.BLACK: m.stats.black++; return new MotorRightState();
		case Color.WHITE: m.stats.white++; return new MotorLeftState();
		case Color.NONE:  return new DoneState();
		default:		  return new WarningState(new UnknownColorWarning(), m, new UnknownColorState());
		}
	}

}
