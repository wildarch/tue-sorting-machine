import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;

public class Main {
	public EV3MediumRegulatedMotor motor;
	public EV3ColorSensor colorSensor;
	public boolean paused = true;
	
	public State currentState;
	
	public Main(){
		Sound.beepSequenceUp();
		motor = new EV3MediumRegulatedMotor(MotorPort.A);
		colorSensor = new EV3ColorSensor(SensorPort.S1);
		
		currentState = new InitialState();
		motor.rotate(120, false);
		
		while(true);
		
		/*while(true){
			if(Button.ESCAPE.isDown()){
				LCD.clear();
				LCD.drawString("ABORT!!!", 0, 0);
				Delay.msDelay(1000);
				System.exit(0);
			} else if(Button.ENTER.isDown()){
				paused = true;
			}
			
			currentState.run(this);
		}*/
	}
	
	public static void main(String[] args){
		new Main();
	}
}
