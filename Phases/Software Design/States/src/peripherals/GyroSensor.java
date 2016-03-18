package peripherals;

public interface GyroSensor {
	public float getRateChange();
	
	public Orientation getOrientation();
	
	public Orientation getOrientation(float angle);
	
	public void reset();
}
