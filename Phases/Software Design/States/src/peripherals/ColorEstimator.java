package peripherals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ColorEstimator {
	public static float noneGS = 0.06f;
	public static float blackGS = 0.12f;
	public static float whiteGS = 0.57f;
	public static float unknownGS = avg(blackGS, whiteGS);

	public static float nbGS = avg(noneGS, blackGS);
	public static float buGS = avg(blackGS, unknownGS);
	public static float uwGS = avg(unknownGS, whiteGS);

	public static String path = "sensorValues.txt";

	public static void calibrate() {
		unknownGS = avg(blackGS, whiteGS);
		nbGS = avg(noneGS, blackGS);
		buGS = avg(blackGS, unknownGS);
		uwGS = avg(unknownGS, whiteGS);
	}

	public static DetectedColor getColor(float sample) {
		if (sample < nbGS) {
			return DetectedColor.NONE;
		} else if (sample < buGS) {
			return DetectedColor.BLACK;
		} else if (sample < uwGS) {
			return DetectedColor.UNKNOWN;
		} else {
			return DetectedColor.WHITE;
		}
	}

	public static float getWrongChance(float sample, DetectedColor detect) {
		float average = 0f;
		float maxDelta = 0f;
		switch (detect) {
		case NONE:
			average = noneGS;
			maxDelta = Math.abs(nbGS - noneGS);
			break;
		case BLACK:
			average = blackGS;
			if (sample < blackGS) {
				maxDelta = Math.abs(blackGS - nbGS);
			} else {
				maxDelta = Math.abs(buGS - blackGS);
			}
			break;
		case UNKNOWN:
			average = unknownGS;
			if (sample < unknownGS) {
				maxDelta = Math.abs(unknownGS - buGS);
				break;
			} else {
				maxDelta = Math.abs(uwGS - unknownGS);
				break;
			}
		case WHITE:
			average = whiteGS;
			maxDelta = Math.abs(whiteGS - uwGS);
			break;
		default:
			break;
		}
		float delta = Math.abs(average - sample);
		return delta / maxDelta;
	}

	public static float avg(float a, float b) {
		return (a + b) / 2;
	}

	public static void readFromFile() {
		File values = new File(path);
		Scanner scanner;
		try {
			scanner = new Scanner(values);
		} catch (IOException e) {
			return;
		}
		noneGS = scanner.nextFloat();
		blackGS = scanner.nextFloat();
		whiteGS = scanner.nextFloat();
		calibrate();
		scanner.close();
	}

	public static void writeToFile(float noneGS, float blackGS, float whiteGS) {
		PrintWriter writer;
		try {
			writer = new PrintWriter(path);
		} catch (FileNotFoundException e) {
			return;
		}
		writer.println(noneGS + "\n" + blackGS + "\n" + whiteGS);
		writer.close();
	}
}
