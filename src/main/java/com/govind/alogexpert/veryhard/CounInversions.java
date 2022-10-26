package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class CounInversions {

	public int countInversions(int[] array) {
		return countSubArrayInversions(array, 0, array.length);
	}

	private int countSubArrayInversions(int[] array, int start, int end) {
		if (end - start <= 1) {
			return 0;
		}

		int middle = start + (end - start) / 2;
		int leftInversions = countSubArrayInversions(array, start, middle);
		int rightInversions = countSubArrayInversions(array, middle, end);
		int mergeArrayInversions = mergeSortAndCountInversions(array, start, middle, end);

		return leftInversions + rightInversions + mergeArrayInversions;
	}

	private int mergeSortAndCountInversions(int[] array, int start, int middle, int end) {
		List<Integer> sortedArray = new ArrayList<Integer>();
		int left = start;
		int right = middle;
		int inversions = 0;

		while (left < middle && right < end) {
			if (array[left] <= array[right]) {
				sortedArray.add(array[left]);
				left += 1;
			} else {
				inversions += middle - left;
				sortedArray.add(array[right]);
				right += 1;
			}
		}

		for (int idx = left; idx < middle; idx++) {
			sortedArray.add(array[idx]);
		}
		for (int idx = right; idx < end; idx++) {
			sortedArray.add(array[idx]);
		}
		for (int idx = 0; idx < sortedArray.size(); idx++) {
			int num = sortedArray.get(idx);
			array[start + idx] = num;
		}
		return inversions;
	}

	public static void main(String[] args) {
		int[] input = new int[] { 2, 3, 3, 1, 9, 5, 6 };
		int expected = 5;
		int actual = new CounInversions().countInversions(input);
		assertTrue(expected == actual);

	}

}
