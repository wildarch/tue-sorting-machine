package sorter;
import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import peripherals.RealBattery;
import peripherals.RealColorSensor;
import peripherals.RealDisplay;
import peripherals.RealGyroSensor;
import peripherals.RealMotor;
import peripherals.RealTouchSensor;
import states.ModeSelectionState;

public class Main extends AbstractMain {
	public static final Port MOTOR_PORT = 			MotorPort.A;
	public static final Port COLOR_SENSOR_PORT = 	SensorPort.S1;
	public static final Port GYRO_SENSOR_PORT = 	SensorPort.S2;
	public static final Port TOUCH_SENSOR_PORT = 	SensorPort.S3;
	
	public Main(){
		super(
				Button.ENTER,
				Button.ESCAPE,
				Button.DOWN,
				new RealMotor(MOTOR_PORT, MOTOR_TURN_STEP, MOTOR_SAFE_SPEED, MOTOR_FAST_SPEED),
				new RealColorSensor(COLOR_SENSOR_PORT),
				new RealGyroSensor(GYRO_SENSOR_PORT, GYRO_STABLE_TRESHOLD),
				new RealTouchSensor(TOUCH_SENSOR_PORT),
				new RealDisplay(),
				new Statistics(),
				new RealBattery()
				);
		Say.hello();
		
		currentState = new ModeSelectionState(this);
		setMode(Mode.FAST);
	}
	
	public static void main(String[] args){
		AbstractMain m = new Main();
		m.run();
	}
}
