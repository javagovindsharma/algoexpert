package com.govind.alogexpert.hard;

import static org.junit.Assert.assertTrue;

public class IndexEqualsValue {

	// solution 3
	public int indexEqualsValue(int[] array) {
		int leftIndex = 0;
		int rightIndex = array.length - 1;

		while (leftIndex <= rightIndex) {

			int middleIndex = rightIndex + (leftIndex - rightIndex) / 2;
			int middleValue = array[middleIndex];

			if (middleValue < middleIndex) {
				leftIndex = middleIndex + 1;
			} else if ((middleValue == middleIndex) && (middleIndex == 0)) {
				return middleIndex;
			} else if ((middleValue == middleIndex) && (array[middleIndex - 1] < (middleIndex - 1))) {
				return middleIndex;
			} else {
				rightIndex = middleIndex - 1;
			}
		}
		return -1;
	}

	// solution 2
	public int indexEqualsValue2(int[] array) {
		return indexEqualsvalueHelper(array, 0, array.length - 1);
	}

	private int indexEqualsvalueHelper(int[] array, int leftIndex, int rightIndex) {

		if (leftIndex > rightIndex) {
			return -1;
		}

		int middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
		int middleValue = array[middleIndex];

		if (middleValue < middleIndex) {
			return indexEqualsvalueHelper(array, middleIndex + 1, rightIndex);
		} else if ((middleValue == middleIndex) && (middleIndex == 0)) {
			return middleIndex;
		} else if ((middleValue == middleIndex) && (array[middleIndex - 1] < (middleIndex - 1))) {
			return middleIndex;
		} else {
			return indexEqualsvalueHelper(array, leftIndex, middleIndex - 1);
		}
	}

	// solution 1
	public int indexEqualsValue1(int[] array) {
		for (int index = 0; index < array.length; index++) {
			int value = array[index];
			if (index == value) {
				return index;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		assertTrue(new IndexEqualsValue().indexEqualsValue(new int[] { -5, -3, 0, 3, 4, 5, 9 }) == 3);

	}

}
