package com.govind.alogexpert.hard;

import java.util.ArrayList;
import java.util.Arrays;

public class MaximunExpression {

	// solution 1
	public int maximizeExpression1(int[] array) {
		if (array.length < 4) {
			return 0;
		}

		int maximumValueFound = Integer.MIN_VALUE;

		for (int a = 0; a < array.length; a++) {
			int aValue = array[a];
			for (int b = a + 1; b < array.length; b++) {
				int bValue = array[b];
				for (int c = b + 1; c < array.length; c++) {
					int cValue = array[c];
					for (int d = c + 1; d < array.length; d++) {
						int dValue = array[d];
						int expressionValue = evaluateExpression(aValue, bValue, cValue, dValue);
						maximumValueFound = Math.max(expressionValue, maximumValueFound);
					}
				}
			}
		}
		return maximumValueFound;
	}

	private int evaluateExpression(int a, int b, int c, int d) {
		return a - b + c - d;
	}

	// solution 2
	public int maximizeExpression(int[] array) {
		if (array.length < 4) {
			return 0;
		}

		ArrayList<Integer> maxOfA = new ArrayList<Integer>(Arrays.asList(array[0]));
		ArrayList<Integer> maxOfAMinusB = new ArrayList<Integer>(Arrays.asList(Integer.MIN_VALUE));
		ArrayList<Integer> maxOfAMinusBPlusC = new ArrayList<Integer>(
				Arrays.asList(Integer.MIN_VALUE, Integer.MIN_VALUE));
		ArrayList<Integer> maxOfAMinusBPlusCMinusD = new ArrayList<Integer>(
				Arrays.asList(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE));

		for (int idx = 1; idx < array.length; idx++) {
			int currentMax = Math.max(maxOfA.get(idx - 1), array[idx]);
			maxOfA.add(currentMax);
		}

		for (int idx = 1; idx < array.length; idx++) {
			int currentMax = Math.max(maxOfAMinusB.get(idx - 1), maxOfA.get(idx - 1) - array[idx]);
			maxOfAMinusB.add(currentMax);
		}

		for (int idx = 2; idx < array.length; idx++) {
			int currentMax = Math.max(maxOfAMinusBPlusC.get(idx - 1), maxOfAMinusB.get(idx - 1) + array[idx]);
			maxOfAMinusBPlusC.add(currentMax);
		}

		for (int idx = 3; idx < array.length; idx++) {
			int currentMax = Math.max(maxOfAMinusBPlusCMinusD.get(idx - 1),
					maxOfAMinusBPlusC.get(idx - 1) - array[idx]);
			maxOfAMinusBPlusCMinusD.add(currentMax);
		}
		return maxOfAMinusBPlusCMinusD.get(maxOfAMinusBPlusCMinusD.size() - 1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
