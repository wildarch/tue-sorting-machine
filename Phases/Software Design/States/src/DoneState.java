import lejos.hardware.Button;

public class DoneState extends State {

	@Override
	public void run(Main m) {
		if(Button.DOWN.isDown()){
			m.currentState = new ReadColorState();
		}
	}
	
}
