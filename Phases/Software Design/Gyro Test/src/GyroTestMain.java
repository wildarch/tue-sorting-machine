import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;

public class GyroTestMain {
	private final float limitAngle = 20;
	private GyroSensor sensor;
	
	public static void main(String[] args) {
		new GyroTestMain().RunTest();
	}

	public void RunTest() {
		sensor = new GyroSensor(SensorPort.S2, limitAngle);
		
		while(true) {
			float angle = sensor.getAngle();
			LCD.clear();
			LCD.drawString("Current angle: " + angle, 0, 0);	
		}
	}
}
