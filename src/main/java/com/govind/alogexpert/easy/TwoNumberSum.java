package com.govind.alogexpert.easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwoNumberSum {

	public static void main(String[] args) {
         int[] array=new int[]{3, 5, -4, 8, 11, 1, -1, 6};
         int targetSum=10;
         System.out.println(Arrays.toString( twoNumberSumSolution1(array, targetSum)));
	}

	public static int[] twoNumberSumSolution1(int[] array, int targetSum) {

		for (int i = 0; i < array.length; i++) {
			int first = array[i];
			for (int j = i + 1; j < array.length; j++) {
				int second = array[j];
				if (first + second == targetSum) {
					return new int[] { first, second };
				}
			}
		}
		return new int[0];
	}

	public static int[] twoNumberSumSolution2(int[] array, int targetSum) {
		Set<Integer> nums = new HashSet<Integer>();
		for (int num : array) {
			int pmatch = targetSum - num;
			if (nums.contains(pmatch)) {
				return new int[] { pmatch, num };
			} else {
				nums.add(num);
			}

		}
		return new int[0];
	}

	public static int[] twoNumberSumSolution3(int[] array, int targetSum) {
		Arrays.sort(array);
		int left = 0;
		int right = array.length - 1;
		while (left < right) {
			int currentSum = array[left] + array[right];
			if (currentSum == targetSum) {
				return new int[] { array[left], array[right] };
			} else if (currentSum < targetSum) {
				left++;
			} else if (currentSum > targetSum) {
				right--;
			}
		}
		return new int[0];
	}
}
