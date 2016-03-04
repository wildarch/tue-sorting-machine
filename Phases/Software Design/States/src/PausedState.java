import lejos.hardware.Button;


public class PausedState extends State {
	boolean released = false;

	@Override
	public void run(Main m) {
		if(Button.ENTER.isDown() && released){
			m.currentState = new ReadColorState();
		}
		else if(Button.ENTER.isUp()){
			released = true;
		}
	}

}
