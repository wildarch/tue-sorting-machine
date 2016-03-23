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
import states.MotorLeftState;
import states.MotorRightState;
import states.ReadColorState;
import states.WarningState;

public class Tests {

	@Test
<<<<<<< HEAD
	public void test() {
		if(true) return;
=======
	public void test() throws InterruptedException {
		
>>>>>>> 526ddb206e3b3697e61712cc2ce8656f1c02fb66
		System.out.println("---Direction test??---");
		
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
		System.out.println("---Direction test?? finished---");
	}
	
	@Test
	public void testReset(){
		System.out.println("---Reset test---");
		MockButton spButton = MockMain.spButton;
		MockTouchSensor touch = MockMain.touch;
		MockColorSensor color = MockMain.color;
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
		System.out.println("---Reset test finished---");
	}
	
	@Test
	public void testMotorJammed() {
		
		System.out.println("---Motor jammed test---");
		
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
		
		System.out.println("---Motor jammed test finished---");
	}
	
	@Test
	public void testWrongBasket() {
		
		System.out.println("---Wrong basket test---");
		
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
	    
	    System.out.println("---Wrong basket test finished---");
	}
	
	@Test
	public void testNoBasket() throws InterruptedException {
		if(true) return;
		System.out.println("---No basket test---");
		
		AbstractMain m = new MockMain();
		
		m.setMode(Mode.SAFE);
		m.currentState = new MotorLeftState(m);
		m.cycle(); // Start timer
		Thread.sleep(m.getTDMax()+1); // Wait for the minimal Disk time
		m.cycle(); // Go to warning state for Deviates From Average warning
		m.cycle(); // Return from warning state
		m.cycle(); // Go to abort state for Does Not Reach Basket fatal error
		assertTrue(m.currentState instanceof AbortState);
		
		System.out.println("---Not basket test finished---");
	}
	
	@Test
	public void testWrongInput() {
		
		System.out.println("---Wrong Input test---");
		
		AbstractMain m = new MockMain();
		MockColorSensor color = MockMain.color;
		
		m.setMode(Mode.SAFE);
		m.currentState = new ReadColorState();
		m.setPaused(false);
		color.setDetectColor(DetectedColor.UNKNOWN);
		m.cycle();
		assertTrue(m.currentState instanceof WarningState);		
		
		System.out.println("---Wrong Input test finished---");
	}
	
	@Test
	public void testBatteryLow() {
		
		System.out.println("---Battery Low test---");
		System.out.println(MockMain.battery.getVoltage());
		
		AbstractMain m = new MockMain();
		MockBattery battery = MockMain.battery;
		battery.setVoltage(AbstractMain.BATTERY_TRESHOLD - 1);
		m.cycle();
		battery.setVoltage(9f);
		
		// Read the console
		
		System.out.println("---Battery Low test finished---");  
		
	}
}
