package peripherals;

public class CloudtEstimator {
	private static final float[] none = new float[]{0.05f, 0.11599f};
	private static final float[] black = new float[]{0.116f, 0.20f};
	private static final float[] white = new float[]{0.56f, 0.95f};

	public static DetectedColor getColor(float sample){
		if (inside(white, sample)){
			System.out.println(sample + " white");
			return DetectedColor.WHITE;
		}
		else if(inside(none, sample)){
			System.out.println(sample + " none");
			return DetectedColor.NONE;
		}
		else if(inside(black, sample)){
			System.out.println(sample + " black");
			return DetectedColor.BLACK;
		}
		else{
			System.out.println(sample + " unknowns");
			return DetectedColor.UNKNOWN;
		}
	}
	
	private static boolean inside(float[] range, float sample){
		return range[0] <= sample && range[1] >= sample;
	}
}
