package com.govind.alogexpert.hard;

import static org.junit.Assert.assertTrue;

public class QuickSort {

	public static int[] quickSort(int[] array) {
		quickSort(array,0,array.length-1);
		return array;
	}

	private static void quickSort(int[] array, int startIdx, int endIdx) {
		 if(startIdx>=endIdx) {
			 return;
		 }
		 
		 int pivotIdx=startIdx;
		 int leftIdx=startIdx+1;
		 int rightIdx=endIdx;
		 
		 while(rightIdx>=leftIdx) {
			 if(array[leftIdx]>array[pivotIdx] && array[rightIdx]<array[pivotIdx]) {
				 swap(leftIdx,rightIdx,array);
			 }
			 if(array[leftIdx]<=array[pivotIdx]) {
				 leftIdx+=1;
			 }
			 if(array[rightIdx]>=array[pivotIdx]) {
				 rightIdx-=1;
			 }
		 }
		swap(pivotIdx, rightIdx, array);
		boolean leftSubarrayIsSmaller=rightIdx-1-startIdx<endIdx-(rightIdx+1);
		if(leftSubarrayIsSmaller) {
			quickSort(array,startIdx,rightIdx-1);
			quickSort(array,rightIdx+1,endIdx);
		}else {
			quickSort(array,rightIdx+1,endIdx);
			quickSort(array,startIdx,rightIdx-1);
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
		assertTrue(compare(QuickSort.quickSort(input), expected));
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
