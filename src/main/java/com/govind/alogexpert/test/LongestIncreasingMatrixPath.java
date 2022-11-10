package com.govind.alogexpert.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

public class LongestIncreasingMatrixPath {

	public static int longestIncreasingMatrixPath(ArrayList<ArrayList<Integer>> matrix) {
		ArrayList<ArrayList<Integer>> longestPathLengths = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < matrix.size(); i++) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			for (int j = 0; j < matrix.get(0).size(); j++) {
				row.add(null);
			}
			longestPathLengths.add(row);
		}
		int longestPathLength = 0;
		for (int row = 0; row < matrix.size(); row++) {
			for (int col = 0; col < matrix.get(0).size(); col++) {
				longestPathLength = Math.max(
						getLogestPathLengthAt(matrix, row, col, Integer.MIN_VALUE, longestPathLengths),
						longestPathLength);
			}
		}
		return longestPathLength;
	}

	private static int getLogestPathLengthAt(ArrayList<ArrayList<Integer>> matrix, int row, int col, int lastPathValue,
			ArrayList<ArrayList<Integer>> longestPathLengths) {
		boolean isOutOfBounds = row < 0 || col < 0 || row >= matrix.size() || col >= matrix.get(0).size();
		if (isOutOfBounds)
			return 0;

		int currentValue = matrix.get(row).get(col);
		if (currentValue <= lastPathValue)
			return 0;

		if (longestPathLengths.get(row).get(col) == null) {
			int candidate1 = getLogestPathLengthAt(matrix, row - 1, col, currentValue, longestPathLengths);
			int candidate2 = getLogestPathLengthAt(matrix, row + 1, col, currentValue, longestPathLengths);
			int candidate3 = getLogestPathLengthAt(matrix, row, col - 1, currentValue, longestPathLengths);
			int candidate4 = getLogestPathLengthAt(matrix, row, col + 1, currentValue, longestPathLengths);

			int max = Math.max(Math.max(candidate1, candidate2), Math.max(candidate3, candidate4));
			longestPathLengths.get(row).set(col, 1 + max);
		}

		return longestPathLengths.get(row).get(col);
	}

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();
		matrix.add(new ArrayList<Integer>(Arrays.asList(1, 2, 4, 3, 2)));
		matrix.add(new ArrayList<Integer>(Arrays.asList(7, 6, 5, 5, 1)));
		matrix.add(new ArrayList<Integer>(Arrays.asList(8, 9, 7, 15, 14)));
		matrix.add(new ArrayList<Integer>(Arrays.asList(5, 10, 11, 12, 13)));
		int expected = 15;
		int actual = LongestIncreasingMatrixPath.longestIncreasingMatrixPath(matrix);
		assertTrue(expected == actual);

	}

}
