package peripherals;

import sorter.AbstractMain;
import sorter.Main;

public interface ColorSensor {
	public float getGrayScale();
	
	public DetectedColor detectColor(float sample);

	public void calibrationSequence(AbstractMain m);
}
