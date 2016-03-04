package sorter;
import states.InitialState;
import states.PausedState;
import states.State;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class Main {
	public Motor motor;
	public ColorSensor colorSensor;
	public boolean paused = true;
	
	private State currentState;
	
	public Main(){
		Sound.beepSequenceUp();
		motor = new Motor(MotorPort.A, 120, 400);
		colorSensor = new ColorSensor(SensorPort.S1);
		
		currentState = new PausedState();
		while(true){
			if(Button.ESCAPE.isDown()){
				motor.stop();
				LCD.clear();
				LCD.drawString("ABORT!!!", 0, 0);
				Delay.msDelay(1000);
				System.exit(0);
			} else if(Button.ENTER.isDown()){
				paused = true;
			}
			State newState = currentState.nextState(this);
			if(newState != currentState){
				Sound.beep();
				LCD.drawString(newState.getClass().getSimpleName(), 0, 0);
				Delay.msDelay(1000);
			}
			currentState = newState;
		}
	}

	public static void main(String[] args){
		new Main();
	}
}
