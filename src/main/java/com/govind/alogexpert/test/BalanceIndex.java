package com.govind.alogexpert.test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

public class BalanceIndex {

	// solution 1
	public static int balanceIndex1(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int leftSideSum = 0;
			int rightSideSum = 0;

			for (int j = 0; j < i; j++) {
				leftSideSum += array[j];
			}
			for (int j = i + 1; j < i; j++) {
				rightSideSum += array[j];
			}
			if (leftSideSum == rightSideSum)
				return i;
		}
		return -1;
	}

	// solution 2
	public static int balanceIndex2(int[] array) {
		int[] leftSideSums = array.clone();
		int leftSideSum = 0;
		for (int i = 0; i < array.length; i++) {
			leftSideSums[i] = leftSideSum;
			leftSideSum+=array[i];
		}
		int rightSideSum = 0;
		for (int i = 0; i < array.length; i++) {
			if(leftSideSums[i] == rightSideSum) return i;
			rightSideSum+=array[i];
		}
		return -1;
	}
	// solution 3
		public static int balanceIndex(int[] array) {
			int arraySum = Arrays.stream(array).sum();
			int leftSideSum = 0;
			int rightSideSum=arraySum;
			for (int i = 0; i < array.length; i++) {
				rightSideSum-=array[i];
				if(leftSideSum == rightSideSum) return i;
				leftSideSum+=array[i];
			}
			return -1;
		}

	public static void main(String[] args) {
		int[] array = new int[] { 0, 9, -8, 2, 7, 1, 11, -2, 1 };
		assertTrue(BalanceIndex.balanceIndex(array) == 5);
	}
}
