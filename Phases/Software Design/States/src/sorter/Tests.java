package sorter;

import static org.junit.Assert.*;

import mock.MockButton;
import mock.MockColorSensor;
import mock.MockGyroSensor;
import mock.MockMain;
import mock.MockMotor;
import mock.MockTouchSensor;

import org.junit.Test;

import peripherals.DetectedColor;
import peripherals.Orientation;

public class Tests {

	@Test
	public void test() {
		MockButton spButton = MockMain.spButton;
		MockButton aButton = MockMain.aButton;
		MockButton rButton = MockMain.rButton;
		
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
		m.cycle();
		//ReadColorState
		color.setDetectColor(DetectedColor.WHITE);
		m.cycle();
		//MotorRightState
		gyro.setRateChange(1000);
		m.cycle();
		motor.stop();
		m.cycle();
		//StabilizeState
		gyro.setRateChange(0);
		m.cycle();
		//ReadColorState
		m.cycle();
		
		color.setDetectColor(DetectedColor.BLACK);
		m.cycle();
		//MotorRightState
		gyro.setRateChange(1000);
		m.cycle();
		motor.stop();
		m.cycle();
		//StabilizeState
		gyro.setRateChange(0);
		m.cycle();
		//ReadColorState
		m.cycle();
	}

}
