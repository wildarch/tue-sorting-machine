package mock;

import sorter.AbstractMain;
import sorter.Mode;
import sorter.Statistics;
import states.InitialState;

public class MockMain extends AbstractMain {
	public static MockButton spButton = new MockButton();
	public static MockButton aButton = new MockButton();
	public static MockButton rButton = new MockButton();
	
	public static MockMotor motor = new MockMotor();
	public static MockGyroSensor gyro = new MockGyroSensor();
	public static MockColorSensor color = new MockColorSensor();
	public static MockTouchSensor touch = new MockTouchSensor();
	public static MockDisplay display = new MockDisplay();
	public static MockBattery battery = new MockBattery();
	
	public MockMain(){
		super(
				spButton,
				aButton,
				rButton,
				motor,
				color,
				gyro,
				touch,
				display,
				new Statistics(),
				battery
			);
		currentState = new InitialState();
		setMode(Mode.FAST);
	}
}
