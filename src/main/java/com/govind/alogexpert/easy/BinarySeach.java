package com.govind.alogexpert.easy;

public class BinarySeach {

	// solution 2
	public static int binarySearch(int[] array, int target) {
		return binarySearch(array, target, 0, array.length - 1);
	}

	private static int binarySearch(int[] array, int target, int left, int right) {
		while (left <= right) {
			int middle = (left + right) / 2;
			int potentialMatch = array[middle];
			if (target == potentialMatch) {
				return middle;
			} else if (target < potentialMatch) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}
		return -1;
	}

	// solution 1
	public static int binarySearch1(int[] array, int target) {
		return binarySearch(array, target, 0, array.length - 1);
	}

	public static void main(String[] args) {
		

	}

}
