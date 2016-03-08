package states;
import sorter.Main;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;


public class InitialState extends State {

	@Override
	public State nextState(Main m) {
		Sound.beep();
		return new ReadColorState();
	}

}
