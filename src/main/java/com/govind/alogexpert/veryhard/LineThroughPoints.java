package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class LineThroughPoints {

	public int lineThroughPoints(int[][] points) {
		int maxNumberOfPointsOnLine = 1;

		for (int idx1 = 0; idx1 < points.length; idx1++) {
			int[] p1 = points[idx1];
			HashMap<String, Integer> slopes = new HashMap<String, Integer>();

			for (int idx2 = idx1 + 1; idx2 < points.length; idx2++) {
				int[] p2 = points[idx2];
				int[] slopOfLineBetweenPoints = getSlopOfLineBetweenPoints(p1, p2);
				int rise = slopOfLineBetweenPoints[0];
				int run = slopOfLineBetweenPoints[1];

				String slopekey = createHashableKeyForRational(rise, run);
				if (!slopes.containsKey(slopekey)) {
					slopes.put(slopekey, 1);
				}
				slopes.put(slopekey, slopes.get(slopekey) + 1);
			}
			int currentMaxNumberOfPointsOnLine = maxSlope(slopes);
			maxNumberOfPointsOnLine = Math.max(maxNumberOfPointsOnLine, currentMaxNumberOfPointsOnLine);
		}
		return maxNumberOfPointsOnLine;
	}

	private int[] getSlopOfLineBetweenPoints(int[] p1, int[] p2) {
		int p1x = p1[0];
		int p1y = p1[1];
		int p2x = p2[0];
		int p2y = p2[1];

		int[] slope = new int[] { 1, 0 };

		if (p1x != p2x) {
			int xDiff = p1x - p2x;
			int yDiff = p1y - p2y;
			int gcd = getGreatestCommonDivisor(Math.abs(xDiff), Math.abs(yDiff));
			xDiff = xDiff / gcd;
			yDiff = yDiff / gcd;
			if (xDiff < 0) {
				xDiff *= -1;
				yDiff *= -1;
			}
			slope = new int[] { yDiff, xDiff };
		}
		return slope;
	}

	private String createHashableKeyForRational(int numerator, int denominator) {
		return String.valueOf(numerator) + ":" + String.valueOf(denominator);
	}

	private int maxSlope(HashMap<String, Integer> slopes) {
		int currentMax = 0;
		for (Map.Entry<String, Integer> slope : slopes.entrySet()) {
			currentMax = Math.max(slope.getValue(), currentMax);
		}
		return currentMax;
	}

	private int getGreatestCommonDivisor(int num1, int num2) {
		int a = num1;
		int b = num2;

		while (true) {
			if (a == 0) {
				return b;
			}
			if (b == 0) {
				return a;
			}
			int temp = a;
			a = b;
			b = temp % b;
		}
	}

	public static void main(String[] args) {
		int[][] input = new int[][] { { 1, 1 }, { 2, 2 }, { 3, 3 }, { 0, 4 }, { -2, 6 }, { 4, 0 }, { 2, 1 } };
		int expected = 4;
		int actual = new LineThroughPoints().lineThroughPoints(input);
		assertTrue(expected == actual);

	}

}
