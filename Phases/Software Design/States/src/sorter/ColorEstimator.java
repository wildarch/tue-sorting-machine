package sorter;

import sorter.DetectedColor;

public class ColorEstimator {
	public final static float noneGS = 		0f;
	public final static float blackGS = 	0.04f;
	public final static float unknownGS = 	0.31f;
	public final static float whiteGS = 	0.58f;
	
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
			case NONE: 		average = noneGS; maxDelta = 0.03f; break;
			case BLACK: 	average = blackGS; 
							if(sample < blackGS){
								maxDelta = 0.10f;
							}
							else {
								maxDelta = 0.16f;
							}
							break;
			case UNKNOWN: 	average = unknownGS; maxDelta = 0.17f; break;
			case WHITE: 	average = whiteGS; maxDelta = 0.17f; break;
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
