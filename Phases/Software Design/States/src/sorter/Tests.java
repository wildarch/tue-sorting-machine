package sorter;

import static org.junit.Assert.*;

import mock.MockButton;
import mock.MockMain;
import mock.MockMotor;
import mock.MockTouchSensor;

import org.junit.Test;

public class Tests {

	@Test
	public void test() {
		MockButton spButton = MockMain.spButton;
		MockButton aButton = MockMain.aButton;
		MockButton rButton = MockMain.rButton;
		
		MockMotor motor = MockMain.motor;
		MockTouchSensor touch = MockMain.touch;
		
		AbstractMain m = new MockMain();
		m.cycle();
		touch.setPressed(true);
		m.cycle();
		spButton.setDown(true);
		m.cycle();
		spButton.setDown(false);
		m.cycle();
		
	}

}
