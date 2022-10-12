package com.govind.alogexpert.hard;

import static org.junit.Assert.assertTrue;

public class ShiftedBinarySearch {

	// solution 2
	public static int shiftedBinarySearch(int[] array, int target) {
		return shiftedBinarySearch(array, target, 0, array.length - 1);
	}

	private static int shiftedBinarySearch(int[] array, int target, int left, int right) {
		while (left <= right) {
			int middle = (left + right) / 2;
			int potentialMatch = array[middle];
			int leftNum = array[left];
			int rightNUm = array[right];

			if (target == potentialMatch) {
				return middle;
			} else if (leftNum <= potentialMatch) {
				if (target < potentialMatch && target >= leftNum) {
					right = middle - 1;
				} else {
					left = middle + 1;
				}
			} else {
				if (target > potentialMatch && target <= rightNUm) {
					left = middle + 1;
				} else {
					right = middle - 1;
				}
			}
		}
		return -1;
	}

	// solution 1
	public static int shiftedBinarySearch1(int[] array, int target) {
		return shiftedBinarySearch1(array, target, 0, array.length - 1);
	}

	private static int shiftedBinarySearch1(int[] array, int target, int left, int right) {
		if (left > right) {
			return -1;
		}

		int middle = (left + right) / 2;
		int potentialMatch = array[middle];
		int leftNum = array[left];
		int rightNUm = array[right];

		if (target == potentialMatch) {
			return middle;
		} else if (leftNum <= potentialMatch) {
			if (target < potentialMatch && target >= leftNum) {
				return shiftedBinarySearch(array, target, left, middle - 1);
			} else {
				return shiftedBinarySearch(array, target, middle + 1, right);
			}
		} else {
			if (target > potentialMatch && target <= rightNUm) {
				return shiftedBinarySearch(array, target, middle + 1, right);
			} else {
				return shiftedBinarySearch(array, target, left, middle - 1);
			}
		}
	}

	public static void main(String[] args) {
		assertTrue(
				ShiftedBinarySearch.shiftedBinarySearch(new int[] { 45, 61, 71, 72, 73, 0, 1, 21, 33, 37 }, 33) == 8);
	}

}
