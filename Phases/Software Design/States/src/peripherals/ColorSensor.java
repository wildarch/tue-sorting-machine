package peripherals;

public interface ColorSensor {
	public float getGrayScale();
	
	public DetectedColor detectColor(float sample);
}
