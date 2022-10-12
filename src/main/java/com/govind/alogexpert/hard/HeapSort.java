package com.govind.alogexpert.hard;

import static org.junit.Assert.assertTrue;

public class HeapSort {
	public static int[] heapSort(int[] array) {
		buildMaxHeap(array);
		for (int endIdx = array.length - 1; endIdx > 0; endIdx--) {
			swap(0, endIdx, array);
			siftDown(0, endIdx - 1, array);
		}

		return array;
	}

	private static void buildMaxHeap(int[] array) {
		int firstParentIdx = (array.length - 2) / 2;
		for (int currentIdx = firstParentIdx; currentIdx >= 0; currentIdx--) {
			siftDown(currentIdx, array.length - 1, array);
		}

	}

	private static void siftDown(int currentIdx, int endIdx, int[] heap) {
		int childOneIdx = currentIdx * 2 + 1;

		while (childOneIdx <= endIdx) {
			int childTwoIdx = currentIdx * 2 + 2 <= endIdx ? currentIdx * 2 + 2 : -1;

			int idxToSwap;
			if (childTwoIdx != -1 && heap[childTwoIdx] > heap[childOneIdx]) {
				idxToSwap = childTwoIdx;
			} else {
				idxToSwap = childOneIdx;
			}

			if (heap[idxToSwap] > heap[currentIdx]) {
				swap(currentIdx, idxToSwap, heap);
				currentIdx = idxToSwap;
				childOneIdx = currentIdx * 2 + 1;
			} else {
				return;
			}
		}

	}

	private static void swap(int i, int j, int[] array) {
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}

	public static void main(String[] args) {
		int[] expected = { 2, 3, 5, 5, 6, 8, 9 };
		int[] input = { 8, 5, 2, 9, 5, 6, 3 };
		assertTrue(compare(HeapSort.heapSort(input), expected));

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
