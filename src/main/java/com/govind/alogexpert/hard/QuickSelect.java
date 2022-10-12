package com.govind.alogexpert.hard;

import static org.junit.Assert.assertTrue;

public class QuickSelect {
	public static int quickselect(int[] array, int k) {
		int position = k - 1;
		return quickselect(array, 0, array.length - 1, position);
	}

	private static int quickselect(int[] array, int startIdx, int endIdx, int position) {

		while (true) {
			if (startIdx > endIdx) {
				throw new RuntimeException("Your Algorithm sholud never arrive here!");
			}

			int pivotIdx = startIdx;
			int leftIdx = startIdx + 1;
			int rightIdx = endIdx;
			while (leftIdx <= rightIdx) {
				if (array[leftIdx] > array[pivotIdx] && array[rightIdx] < array[pivotIdx]) {
					swap(leftIdx, rightIdx, array);
				}
				if (array[leftIdx] <= array[pivotIdx]) {
					leftIdx += 1;
				}
				if (array[rightIdx] >= array[pivotIdx]) {
					rightIdx -= 1;
				}
			}
			swap(pivotIdx, rightIdx, array);
			if (rightIdx == position) {
				return array[rightIdx];
			} else if (rightIdx < position) {
				startIdx = rightIdx + 1;
			} else {
				endIdx = rightIdx - 1;
			}
		}
	}

	private static void swap(int i, int j, int[] array) {
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}

	public static void main(String[] args) {
		assertTrue(QuickSelect.quickselect(new int[] { 8, 5, 2, 9, 7, 6, 3 }, 3) == 5);

	}

}
