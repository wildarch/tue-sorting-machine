package peripherals;


public class ColorEstimator {
	public final static float noneGS = 		0.11f;
	public final static float blackGS = 	0.155f;
	public final static float whiteGS = 	0.60f;
	public final static float unknownGS = 	avg(blackGS, whiteGS);
	
	public final static float nbGS = avg(noneGS, blackGS);
	public final static float buGS = avg(blackGS, unknownGS);
	public final static float uwGS = avg(unknownGS, whiteGS);
	
	public static DetectedColor getColor(float sample){
		if(sample < nbGS){
			return DetectedColor.NONE;
		}
		else if (sample < buGS){
			return DetectedColor.BLACK;
		}
		else if(sample < uwGS){
			return DetectedColor.UNKNOWN;
		}
		else {
			return DetectedColor.WHITE;
		}
	}
	
	public static float getWrongChance(float sample, DetectedColor detect){
		float average = 0f;
		float maxDelta = 0f;
		switch(detect){
			case NONE: 		average = noneGS; maxDelta = Math.abs(nbGS - noneGS); break;
			case BLACK: 	average = blackGS; 
							if(sample < blackGS){
								maxDelta = Math.abs(blackGS - nbGS);
							}
							else {
								maxDelta = Math.abs(buGS - blackGS);
							}
							break;
			case UNKNOWN: 	average = unknownGS;
							if(sample < unknownGS){
								maxDelta = Math.abs(unknownGS - buGS); break;
							}
							else {
								maxDelta = Math.abs(uwGS - unknownGS); break;
							}
			case WHITE: 	average = whiteGS; maxDelta = Math.abs(whiteGS - uwGS); break;
		default:
			break;
		}
		float delta = Math.abs(average - sample);
		return delta/maxDelta;
	}
	
	public static float avg(float a, float b){
		return (a+b)/2;
	}
}
