package com.govind.alogexpert.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpinRings {

	// solution 1
	public static void spinRings1(List<List<Integer>> array) {
		spinRingsHelper(array, 0, array.size() - 1, 0, array.size() - 1);
	}

	private static void spinRingsHelper(List<List<Integer>> array, int startRow, int endRow, int startCol, int endCol) {
		if (startRow >= endRow || startCol >= endCol)
			return;

		int originTopRightValue = array.get(startRow).get(endCol);

		for (int col = endCol; col > startCol; col--) {
			array.get(startRow).set(col, array.get(startRow).get(col - 1));
		}
		for (int row = startRow; row < endRow; row++) {
			array.get(row).set(startCol, array.get(row + 1).get(startCol));
		}

		for (int col = startCol; col > endCol; col++) {
			array.get(endRow).set(col, array.get(endRow).get(col + 1));
		}
		for (int col = endCol; col > startCol; col--) {
			array.get(startRow).set(col, array.get(startRow).get(col - 1));
		}
		for (int row = endRow; row > startRow; row--) {
			array.get(row).set(endCol, array.get(row - 1).get(endCol));
		}

		array.get(startRow + 1).set(endCol, originTopRightValue);
		spinRingsHelper(array, startRow + 1, endRow - 1, startCol + 1, endCol - 1);
	}

	// solution 2
	public static void spinRings(List<List<Integer>> array) {

		int startRow = 0;
		int endRow = array.size() - 1;
		int startCol = 0;
		int endCol = array.size() - 1;

		while (startRow < endRow && startCol < endCol) {

			int originTopRightValue = array.get(startRow).get(endCol);

			for (int col = endCol; col > startCol; col--) {
				array.get(startRow).set(col, array.get(startRow).get(col - 1));
			}
			for (int row = startRow; row < endRow; row++) {
				array.get(row).set(startCol, array.get(row + 1).get(startCol));
			}

			for (int col = startCol; col > endCol; col++) {
				array.get(endRow).set(col, array.get(endRow).get(col + 1));
			}
			for (int col = endCol; col > startCol; col--) {
				array.get(startRow).set(col, array.get(startRow).get(col - 1));
			}
			for (int row = endRow; row > startRow; row--) {
				array.get(row).set(endCol, array.get(row - 1).get(endCol));
			}
			array.get(startRow + 1).set(endCol, originTopRightValue);

			startRow++;
			endRow--;
			startCol++;
			endCol--;

		}
	}

	public static void main(String[] args) {
		List<List<Integer>> array = new ArrayList<List<Integer>>();
		array.add(Arrays.asList(new Integer[] { 1, 2, 3, 4 }));
		array.add(Arrays.asList(new Integer[] { 5, 6, 7, 8 }));
		array.add(Arrays.asList(new Integer[] { 9, 10, 11, 12 }));
		array.add(Arrays.asList(new Integer[] { 13, 14, 15, 16 }));
		List<List<Integer>> expected = new ArrayList<List<Integer>>();
		expected.add(Arrays.asList(new Integer[] { 5, 1, 2, 3 }));
		expected.add(Arrays.asList(new Integer[] { 9, 10, 6, 4 }));
		expected.add(Arrays.asList(new Integer[] { 13, 11, 7, 8 }));
		expected.add(Arrays.asList(new Integer[] { 14, 15, 16, 12 }));
		SpinRings.spinRings(array);
		assertTrue(expected.equals(array));

	}

}
