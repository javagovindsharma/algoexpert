package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.HashMap;

public class StairCaseTraversal {
	// solution 1
	public int staircaseTraversal(int height, int maxSteps) {
		int currentNumberOfWays = 0;
		ArrayList<Integer> waysToTop = new ArrayList<Integer>();
		waysToTop.add(1);
		for (int currentHeight = 2; currentHeight < height + 1; currentHeight++) {
			int startOfWindow = currentHeight - maxSteps - 1;
			int endOfWindow = currentHeight - 1;
			if (startOfWindow >= 0) {
				currentNumberOfWays -= waysToTop.get(startOfWindow);
			}
			currentNumberOfWays += waysToTop.get(endOfWindow);
			waysToTop.add(currentNumberOfWays);
		}
		return waysToTop.get(height);
	}

	// solution 2
	public int staircaseTraversal2(int height, int maxSteps) {
		int[] waysToTop = new int[height + 1];
		waysToTop[0] = 1;
		waysToTop[1] = 1;
		for (int currentHeight = 2; currentHeight < height + 1; currentHeight++) {
			int step = 1;
			while (step <= maxSteps && step <= currentHeight) {
				waysToTop[currentHeight] = waysToTop[currentHeight] + waysToTop[currentHeight - step];
				step += 1;
			}
		}
		return waysToTop[height];
	}

	// solution 3
	public int staircaseTraversal3(int height, int maxSteps) {
		HashMap<Integer, Integer> memoize = new HashMap<Integer, Integer>();
		memoize.put(0, 1);
		memoize.put(1, 1);
		return numberOfWaysToTop(height, maxSteps, memoize);
	}

	private int numberOfWaysToTop(int height, int maxSteps, HashMap<Integer, Integer> memoize) {
		if (memoize.containsKey(height)) {
			return memoize.get(height);
		}
		int numberOfWays = 0;
		for (int step = 1; step < Math.min(maxSteps, height) + 1; step++) {
			numberOfWays += numberOfWaysToTop(height - step, maxSteps, memoize);
		}
		memoize.put(height, numberOfWays);

		return numberOfWays;
	}

	// solution 4
	public int staircaseTraversal4(int height, int maxSteps) {
		return numberOfWaysToTop(height, maxSteps);
	}

	private int numberOfWaysToTop(int height, int maxSteps) {
		if (height <= 1) {
			return 1;
		}
		int numberOfWays = 0;
		for (int step = 1; step < Math.min(maxSteps, height) + 1; step++) {
			numberOfWays += numberOfWaysToTop(height - step, maxSteps);
		}
		return numberOfWays;
	}

	public static void main(String[] args) {
		int stairs = 4;
		int maxSteps = 2;
		int expected = 5;
		int actual = new StairCaseTraversal().staircaseTraversal(stairs, maxSteps);
		System.out.println(expected + "== " + actual);

	}

}
