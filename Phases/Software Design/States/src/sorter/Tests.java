package sorter;

import static org.junit.Assert.assertTrue;
import mock.MockButton;
import mock.MockColorSensor;
import mock.MockGyroSensor;
import mock.MockMain;
import mock.MockMotor;
import mock.MockTouchSensor;

import org.junit.Test;

import peripherals.DetectedColor;
import states.MotorLeftState;
import states.MotorRightState;
import states.ReadColorState;

public class Tests {

	@Test
	public void test() {
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
		m.cycle();
		//ReadColorState
		assertTrue(m.currentState instanceof ReadColorState);
		m.cycle();
		System.out.println("---Test successfull---");
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

}
