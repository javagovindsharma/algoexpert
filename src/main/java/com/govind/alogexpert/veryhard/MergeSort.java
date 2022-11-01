package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

public class MergeSort {

	// solution 1
	public static int[] mergeSort1(int[] array) {
		if (array.length <= 1) {
			return array;
		}
		int middleIdx = array.length / 2;
		int[] leftHalf = Arrays.copyOfRange(array, 0, middleIdx);
		int[] rightHalf = Arrays.copyOfRange(array, middleIdx, array.length);
		return mergeSortedArrays(mergeSort(leftHalf), mergeSort(rightHalf));
	}

	private static int[] mergeSortedArrays(int[] leftHalf, int[] rightHalf) {
		int[] sortedArray = new int[leftHalf.length + rightHalf.length];
		int k = 0, i = 0, j = 0;
		while (i < leftHalf.length && j < rightHalf.length) {
			if (leftHalf[i] <= rightHalf[j]) {
				sortedArray[k++] = leftHalf[i++];
			} else {
				sortedArray[k++] = rightHalf[j++];
			}
		}
		while (i < leftHalf.length) {
			sortedArray[k++] = leftHalf[i++];
		}
		while (j < rightHalf.length) {
			sortedArray[k++] = rightHalf[j++];
		}

		return sortedArray;
	}

	// solution 2
	public static int[] mergeSort(int[] array) {
		if (array.length <= 1) {
			return array;
		}
		int[] auxiliaryArray = array.clone();
		mergeSort(array, 0, array.length - 1, auxiliaryArray);
		return array;
	}

	private static void mergeSort(int[] mainArray, int startIdx, int endIdx, int[] auxiliaryArray) {
		if (startIdx == endIdx) {
			return;
		}
		int middleIdx = (startIdx + endIdx) / 2;
		mergeSort(auxiliaryArray, startIdx, middleIdx, mainArray);
		mergeSort(auxiliaryArray, middleIdx + 1, endIdx, mainArray);
		doMerge(mainArray, startIdx, middleIdx, endIdx, auxiliaryArray);
	}

	private static void doMerge(int[] mainArray, int startIdx, int middleIdx, int endIdx, int[] auxiliaryArray) {
		int k = startIdx;
		int i = startIdx;
		int j = middleIdx + 1;

		while (i <= middleIdx && j <= endIdx) {
			if (auxiliaryArray[i] <= auxiliaryArray[j]) {
				mainArray[k++] = auxiliaryArray[i++];
			} else {
				mainArray[k++] = auxiliaryArray[j++];
			}
		}
		while (i <= middleIdx) {
			mainArray[k++] = auxiliaryArray[i++];
		}
		while (j <= endIdx) {
			mainArray[k++] = auxiliaryArray[j++];
		}

	}

	public static void main(String[] args) {
		int[] expected = { 2, 3, 5, 5, 6, 8, 9 };
		int[] input = { 8, 5, 2, 9, 5, 6, 3 };
		assertTrue(compare(MergeSort.mergeSort(input), expected));

	}

	public static boolean compare(int[] arr1, int[] arr2) {
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

}
