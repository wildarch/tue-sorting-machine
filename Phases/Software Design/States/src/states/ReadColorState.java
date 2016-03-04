package states;
import sorter.Main;
import lejos.robotics.Color;

public class ReadColorState extends State {

	@Override
	public State nextState(Main m) {
		if(m.paused){
			return new PausedState();
		}

		int color = m.colorSensor.readColor();
		switch(color){
		case Color.BLACK: return new BlackState();
		case Color.WHITE: return new WhiteState();
		case Color.NONE:  return new DoneState();
		default:		  return new UnknownColorState();
		}
	}

}
