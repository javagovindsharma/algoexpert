package com.govind.alogexpert.medum;

public class NumberOfWayGraphTravel {

	//solution 1
	public int numberOfWaysToTraverseGraph(int width, int height) {
	     int xDistanceToCorner = width -1;
	     int yDistanceToCorner = height -1;
	     int numerator =factorial(xDistanceToCorner+yDistanceToCorner);
	     int denominator =factorial(xDistanceToCorner)*factorial(yDistanceToCorner);
	     return numerator/denominator;
	}
	public int factorial(int num) {
		int result=1;
		for(int n=2;n<num+1;n++) {
			result*=n;
		}
		return result;
	}
	// solution 2
	public int numberOfWaysToTraverseGraph1(int width, int height) {
		int[][] numberOfWay = new int[height + 1][width + 1];
		for (int widthIdx = 1; widthIdx < width + 1; widthIdx++) {
			for (int heightIdx = 1; heightIdx < height + 1; heightIdx++) {
				if (widthIdx == 1 || heightIdx == 1) {
					numberOfWay[heightIdx][widthIdx] = 1;
				} else {
					int waysLeft = numberOfWay[heightIdx][widthIdx - 1];
					int waysUp = numberOfWay[heightIdx - 1][widthIdx];
					numberOfWay[heightIdx][widthIdx] = waysLeft + waysUp;
				}
			}
		}
		return numberOfWay[height][width];
	}

	// solution 3
	public int numberOfWaysToTraverseGraph2(int width, int height) {
		if (width == 1 || height == 1) {
			return 1;
		}
		return numberOfWaysToTraverseGraph2(width - 1, height) + numberOfWaysToTraverseGraph(width, height - 1);
	}

	public static void main(String[] args) {
		int width = 4;
		int height = 3;
		int expected = 10;
		Object actual = new NumberOfWayGraphTravel().numberOfWaysToTraverseGraph(width, height);
		System.out.println(expected + "" + actual);

	}

}
