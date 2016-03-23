package mock;

import peripherals.ColorSensor;
import peripherals.DetectedColor;
import sorter.AbstractMain;

public class MockColorSensor implements ColorSensor {
	
	private float grayScale = 0f;
	private DetectedColor detectedColor = DetectedColor.NONE;

	public float getGrayScale() {
		return grayScale;
	}
	
	public void setGrayScale(float g){
		grayScale = g;
	}

	public DetectedColor detectColor(float sample) {
		return detectedColor;
	}
	
	public void setDetectColor(DetectedColor c){
		detectedColor = c;
	}

	public void calibrationSequence(AbstractMain m) {
		System.out.println("Calibration sequence");
	}

}
