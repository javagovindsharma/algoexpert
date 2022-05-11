package com.govind.alogexpert.medum;

public class MonotonicArrayTest {

	public static boolean isMonotonic(int[] array) {
		boolean isNonDecreasing = true;
		boolean isNonIncreasing = true;

		for (int i = 1; i < array.length; i++) {
			if (array[i] < array[i - 1]) {
				isNonDecreasing = false;
			}
			if (array[i] > array[i - 1]) {
				isNonIncreasing = false;
			}
		}
		return isNonDecreasing || isNonIncreasing;
	}

	public static boolean isMonotonic1(int[] array) {
		if (array.length <= 2)
			return true;
		int direction = array[1] - array[0];

		for (int i = 2; i < array.length; i++) {
			if (direction == 0) {
				direction = array[i] - array[i - 1];
				continue;
			}
			if (breakDirection(direction, array[i], array[i - 1])) {
				return false;
			}
		}
		return true;
	}

	private static boolean breakDirection(int direction, int previous, int current) {
		int different = current - previous;
		if (direction > 0)
			return different < 0;
		return different > 0;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { -1, -5, -10, -1100, -1100, -1101, -1102, -9001 };
		System.out.println(MonotonicArrayTest.isMonotonic(arr));

	}

}
