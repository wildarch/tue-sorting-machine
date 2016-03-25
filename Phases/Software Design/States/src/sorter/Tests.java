package sorter;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import mock.MockBattery;
import mock.MockButton;
import mock.MockColorSensor;
import mock.MockGyroSensor;
import mock.MockMain;
import mock.MockMotor;
import mock.MockTouchSensor;
import peripherals.DetectedColor;
import states.AbortState;
import states.DoneState;
import states.InitialState;
import states.ModeSelectionState;
import states.MotorLeftState;
import states.MotorRightState;
import states.ReadColorState;
import states.StabilizeState;
import states.WarningState;

public class Tests {

	@Test
	public void testSortingProcess() throws InterruptedException {
		
		System.out.println("---testSortingProcess start---");
		
		MockButton spButton = MockMain.spButton;
		MockMotor motor = MockMain.motor;
		MockTouchSensor touch = MockMain.touch;
		MockColorSensor color = MockMain.color;
		MockGyroSensor gyro = MockMain.gyro;
		
		AbstractMain m = new MockMain();
		m.setMode(Mode.SAFE);
		m.cycle();
		touch.setPressed(true);
		m.cycle();
		spButton.setDown(true);
		m.cycle();
		spButton.setDown(false);
		color.setDetectColor(DetectedColor.WHITE);
		m.cycle();
		//ReadColorState
		assertTrue(m.currentState instanceof ReadColorState);
		m.cycle();
		//MotorRightState
		assertTrue(m.currentState instanceof MotorRightState);
		
		gyro.setRateChange(1000);
		m.cycle();
		motor.stop();
		m.cycle();
		//StabilizeState
		gyro.setRateChange(0);
		Thread.sleep(1001); // Wait MIN_TIMER
		m.cycle();
		//ReadColorState
		color.setDetectColor(DetectedColor.BLACK);
		m.cycle();
		m.cycle();
		//MotorLeftState
		assertTrue(m.currentState instanceof MotorLeftState);
		gyro.setRateChange(-1000);
		m.cycle();
		motor.stop();
		m.cycle();
		//StabilizeState
		gyro.setRateChange(0);
		Thread.sleep(1001); // Wait MIN_TIMER
		m.cycle();
		//ReadColorState
		assertTrue(m.currentState instanceof ReadColorState);
		m.cycle();
		System.out.println("---testSortingProcess   end---");
	}
	
	@Test
	public void testColorSensor() {
		
		System.out.println("---testColorSensor start---");
		
		AbstractMain m = new MockMain();
		MockColorSensor color = MockMain.color;
		
		m.currentState = new ReadColorState();
		m.setPaused(false);
		m.setReset(false);
		color.setDetectColor(DetectedColor.BLACK);
		m.cycle();
		assertTrue(m.currentState instanceof MotorLeftState);
		
		m.currentState = new ReadColorState();
		m.setPaused(false);
		m.setReset(false);
		color.setDetectColor(DetectedColor.WHITE);
		m.cycle();
		assertTrue(m.currentState instanceof MotorRightState);
		
		m.currentState = new ReadColorState();
		m.setPaused(false);
		m.setReset(false);
		color.setDetectColor(DetectedColor.NONE);
		m.cycle();
		assertTrue(m.currentState instanceof DoneState);
		
		m.currentState = new ReadColorState();
		m.setPaused(false);
		m.setReset(false);
		color.setDetectColor(DetectedColor.UNKNOWN);
		m.cycle();
		assertTrue(m.currentState instanceof WarningState);
		
		System.out.println("---testColorSensor   end---");
	}
	
	@Test
	public void testMotorCalibration() {
		
		System.out.println("---testMotorCalibration start---");
		
		AbstractMain m = new MockMain();
		MockButton spbutton = MockMain.spButton;
		MockMotor motor = MockMain.motor;
		MockTouchSensor touch = MockMain.touch;
		
		m.currentState = new InitialState();
		spbutton.setDown(false);
		touch.setPressed(false);
		m.cycle();
		
		assertTrue(motor.isMoving());
		
		touch.setPressed(true);
		m.cycle();
		touch.setPressed(false);
		
		assertTrue(m.currentState instanceof InitialState);
		
		spbutton.setDown(true);
		m.cycle();
		spbutton.setDown(false);
		m.cycle();
		
		assertTrue(m.currentState instanceof ReadColorState);
		
		System.out.println("---testMotorCalibration   end---");
	}
	
	@Test
	public void testReset(){
		System.out.println("---testReset start---");
		MockButton rButton = MockMain.rButton;
		
		AbstractMain m = new MockMain();
		m.currentState = new ReadColorState();
		m.setPaused(false);
		rButton.setDown(true);
		m.cycle();
		rButton.setDown(false);
		
		assertTrue(m.currentState instanceof ModeSelectionState);
		
		System.out.println("---testReset   end---");
	}
	
	// Motor jammed error
	@Test
	public void testMotorJammed() {
		
		System.out.println("---testMotorJammed start---");
		
		AbstractMain m = new MockMain();
		MockMotor motor = MockMain.motor;

		m.setMode(Mode.FAST);
		m.currentState = new MotorLeftState(m);			
		m.cycle();
		motor.setStalled(true);
		m.cycle();
		assertTrue(m.currentState instanceof AbortState);
		
		m.setMode(Mode.SAFE);
		m.currentState = new MotorLeftState(m);			
		m.cycle();
		motor.setStalled(true);
		m.cycle();	
		assertTrue(m.currentState instanceof AbortState);
		
		m.setMode(Mode.INCREMENTAL);
		m.currentState = new MotorLeftState(m);			
		m.cycle();
		motor.setStalled(true);
		m.cycle();	
		assertTrue(m.currentState instanceof AbortState);
		
		System.out.println("---testMotorJammed   end---");
	}
	
	// Wrong basket error
	@Test
	public void testWrongBasket() {
		
		System.out.println("---testWrongBasket start---");
		
		AbstractMain m = new MockMain();
		MockGyroSensor gyro = MockMain.gyro;
		
		m.setMode(Mode.SAFE);
		m.currentState = new MotorLeftState(m); // Indicates disc should fall left
		gyro.setRateChange(100);				// Indicates disc fell right
		m.cycle();
	    assertTrue(m.currentState instanceof WarningState);
	    
	    m.setMode(Mode.INCREMENTAL);
		m.currentState = new MotorLeftState(m); // Indicates disc should fall left
		gyro.setRateChange(100);				// Indicates disc fell right
		m.cycle();
	    assertTrue(m.currentState instanceof WarningState);
	    
	    gyro.setRateChange(0);
	    
	    System.out.println("---testWrongBasket   end---");
	}
	
	// Does not arrive in basket
	@Test
	public void testNoBasket() throws InterruptedException {
		
		System.out.println("---testNoBasket start---");
		
		AbstractMain m = new MockMain();
		
		m.setMode(Mode.SAFE);
		m.currentState = new MotorLeftState(m);
		m.cycle(); // Start timer
		Thread.sleep(m.getTDMax()+1); // Wait for the minimal Disk time
		m.cycle(); // Go to warning state for Deviates From Average warning
		m.cycle(); // Return from warning state
		m.cycle(); // Go to abort state for Does Not Reach Basket fatal error
		assertTrue(m.currentState instanceof AbortState);
		
		System.out.println("---testNoBasket   end---");
	}
	
	// Wrong input error
	@Test
	public void testWrongInput() {
		
		System.out.println("---testWrongInput start---");
		
		AbstractMain m = new MockMain();
		MockColorSensor color = MockMain.color;
		
		m.setMode(Mode.SAFE);
		m.currentState = new ReadColorState();
		m.setPaused(false);
		color.setDetectColor(DetectedColor.UNKNOWN);
		m.cycle();
		assertTrue(m.currentState instanceof WarningState);		
		
		System.out.println("---testWrongInput   end---");
	}
	
	// Battery low warning
	@Test
	public void testBatteryLow() {
		
		System.out.println("---testBatteryLow start---");
		
		AbstractMain m = new MockMain();
		MockBattery battery = MockMain.battery;
		battery.setVoltage(AbstractMain.BATTERY_TRESHOLD - 1);
		m.cycle();
		battery.setVoltage(9f);
		
		// Read the console
		
		System.out.println("---testBatteryLow   end---");  
		
	}
	
	// Deviates from average warning
	@Test
	public void testDeviatesFromAverage() throws InterruptedException {
		 System.out.println("---testDeviatesFromAverage start---");
		 
		 AbstractMain m = new MockMain();
		 m.setMode(Mode.SAFE);
		 m.currentState = new MotorLeftState(m);
		 
		 m.cycle(); // Start timer
		 Thread.sleep(m.getTAvg() + 1); // Wait for the average time
		 m.cycle();
		 assertTrue(m.currentState instanceof WarningState);
		 
		 System.out.println("---testDeviatesFromAverage   end---");
	}
	
	// Abort by user
	@Test
	public void testAbort() {
		
		System.out.println("---testAbort start---");
		
		AbstractMain m = new MockMain();
		MockButton button = MockMain.aButton;
		button.setDown(true);
		m.cycle();
		button.setDown(false);
		assertTrue(m.currentState instanceof AbortState);
		
		System.out.println("---testAbort  end---");
		
	}
	
	// Gyroscope does not stabilize
	@Test
	public void testGyroDoesNotStabilize() throws InterruptedException {
		
		System.out.println("---testGyroDoesNotStabilize start---");
		
		AbstractMain m = new MockMain();
		m.currentState = new StabilizeState();
		m.cycle();
		Thread.sleep(m.getTGMax()+1);
		m.cycle();
		
		assertTrue(m.currentState instanceof AbortState);
		
		System.out.println("---testGyroDoesNotStabilize   end---");
	}
	
	
}
