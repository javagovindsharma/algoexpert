package com.govind.alogexpert.medum;

import java.util.Arrays;

public class SmallestDifference {

	public int[] smallestDiff(int[] arrayOne, int[] arrayTwo) {
		Arrays.sort(arrayOne);
		Arrays.sort(arrayTwo);
		int i = 0, j = 0;
		int current = Integer.MAX_VALUE, smallest = Integer.MAX_VALUE;
		int[] smallestPair = new int[2];
		while (i < arrayOne.length && j < arrayTwo.length) {
			int first = arrayOne[i];
			int second = arrayTwo[j];
			if (first < second) {
				current = Math.abs(first - second);
				i++;
			} else if (second < first) {
				current = Math.abs(second - first);
				j++;
			} else {
				return new int[] { first, second };
			}

			if (smallest > current) {
				smallest = current;
				smallestPair = new int[] { first, second };
			}
		}

		return smallestPair;

	}

	public static void main(String[] args) {
		int[] arrayOne = new int[] { -1, 5, 10, 20, 28, 3 };
		int[] arrayTwo = new int[] { 26, 134, 135, 15, 17 };
		SmallestDifference obj = new SmallestDifference();
		System.out.println(Arrays.toString(obj.smallestDiff(arrayOne, arrayTwo)));
	}
}
