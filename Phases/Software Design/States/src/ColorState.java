import lejos.hardware.lcd.LCD;

public abstract class ColorState extends State {	
	public ColorState(int r, State s){
	}

	@Override
	public void run(Main m) {
		//LCD.drawString(""+m.motor.isMoving(), 0, 0);
		
		if(!m.motor.isMoving()){
			LCD.drawString("finished", 0, 0);
		}
	}
}
