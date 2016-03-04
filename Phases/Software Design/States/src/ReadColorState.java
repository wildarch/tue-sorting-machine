import lejos.robotics.Color;

public class ReadColorState extends State {

	@Override
	public void run(Main m) {
		if(m.paused){
			m.currentState = new PausedState();
		}
		
		float[] sample = new float[1];
		m.colorSensor.fetchSample(sample, 0);
		int color = (int) sample[0];
		if(color == Color.BLACK || color == Color.BROWN){
			m.currentState = new BlackState();
			m.motor.rotate(120, true);
		}
		else if(color == Color.WHITE){
			m.currentState = new WhiteState();
			m.motor.rotate(-120, true);
		}
		else if (color == Color.NONE){
			m.currentState = new DoneState();
		}
		else {
			m.currentState = new WhiteState();
			m.motor.rotate(-120, true);
		}
	}

}
