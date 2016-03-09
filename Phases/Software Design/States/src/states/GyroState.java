package states;

import sorter.Main;
import sorter.Orientation;
import error.WrongBasketWarning;

public abstract class GyroState extends State {
	
	private boolean hit = false;
	private Orientation desiredOrientation;
	
	public GyroState(Orientation o){
		desiredOrientation = o;
	}

	@Override
	public State run(Main m) {
		Orientation orient = m.gyroSensor.getOrientation();
		if(!hit && orient != Orientation.Neutral){
			hit = true;
			if(orient != desiredOrientation){
				//Surpress further warnings
				desiredOrientation = orient;
				return new WarningState(new WrongBasketWarning(), m, new ReadColorState());
			}
		}
		else if(hit && orient == Orientation.Neutral){
			return new ReadColorState();
		}
		
		return this;
	}

}
