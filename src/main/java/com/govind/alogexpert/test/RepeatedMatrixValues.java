package com.govind.alogexpert.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepeatedMatrixValues {

	public static List<Integer> repeatedMatrixValues(ArrayList<ArrayList<Integer>> matrix) {
		Map<Integer, Integer> valueCounts = initializeCountsOfPotentialValues(matrix);
		for (int row = 0; row < matrix.size(); row++) {
			for (int col = 0; col < matrix.get(0).size(); col++) {
				int value = matrix.get(row).get(col);
				int correctCountSoFar = row;
				checkAndIncrementValueCount(value, valueCounts, correctCountSoFar);
			}
		}
		for (int col = 0; col < matrix.get(0).size(); col++) {
			for (int row = 0; row < matrix.size(); row++) {
				int value = matrix.get(row).get(col);
				int correctCountSoFar = matrix.size() + col;
				checkAndIncrementValueCount(value, valueCounts, correctCountSoFar);
			}
		}
		List<Integer> finalValues = new ArrayList<Integer>();
		for (Integer value : valueCounts.keySet()) {
			Integer count = valueCounts.get(value);
			if (count.equals(matrix.size() + matrix.get(0).size())) {
				finalValues.add(value);
			}
		}
		return finalValues;
	}

	private static Map<Integer, Integer> initializeCountsOfPotentialValues(ArrayList<ArrayList<Integer>> matrix) {
		Map<Integer, Integer> valueCounts = new HashMap<Integer, Integer>();
		ArrayList<Integer> smallerSide = matrix.get(0);
		if (matrix.size() < matrix.get(0).size()) {
			smallerSide = new ArrayList<Integer>();
			for (ArrayList<Integer> arr : matrix) {
				smallerSide.add(arr.get(0));
			}
		}
		for (Integer value : smallerSide) {
			valueCounts.put(value, 0);
		}
		return valueCounts;
	}

	private static void checkAndIncrementValueCount(int value, Map<Integer, Integer> valueCounts,
			int correctCountSoFar) {
		if (!valueCounts.containsKey(value))
			return;
		if (!valueCounts.get(value).equals(correctCountSoFar))
			return;
		valueCounts.put(value, valueCounts.get(value) + 1);

	}

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
		matrix.add(new ArrayList<Integer>(Arrays.asList(1, 3, 7, 4, 5)));
		matrix.add(new ArrayList<Integer>(Arrays.asList(2, 5, 9, 3, 3)));
		matrix.add(new ArrayList<Integer>(Arrays.asList(1, 8, 5, 3, 5)));
		matrix.add(new ArrayList<Integer>(Arrays.asList(5, 0, 3, 5, 6)));
		matrix.add(new ArrayList<Integer>(Arrays.asList(3, 8, 3, 5, 6)));
		matrix.add(new ArrayList<Integer>(Arrays.asList(1, 0, 3, 0, 5)));
		List<Integer> expected = new ArrayList<Integer>(Arrays.asList(3, 5));
		List<Integer> actual = RepeatedMatrixValues.repeatedMatrixValues(matrix);
		assertTrue(expected.equals(actual));

	}

}
