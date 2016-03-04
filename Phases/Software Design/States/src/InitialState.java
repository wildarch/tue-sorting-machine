import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;


public class InitialState extends State {

	@Override
	public void run(Main m) {
		if(Button.ENTER.isDown()){
			Sound.beep();
			m.currentState = new ReadColorState();
		}
	}

}
