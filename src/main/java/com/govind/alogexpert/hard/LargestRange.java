package com.govind.alogexpert.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LargestRange {

	public static int[] largestRange(int[] array) {
		int[] bestRange = new int[2];
		int longestLength = 0;
		Map<Integer, Boolean> nums = new HashMap<Integer, Boolean>();
		for (int num : array) {
			nums.put(num, true);
		}
		for (int num : array) {
			if (!nums.get(num)) {
				continue;
			}
			nums.put(num, false);
			int currentLength = 1;
			int left = num - 1;
			int right = num + 1;
			while (nums.containsKey(left)) {
				nums.put(left, false);
				currentLength++;
				left--;
			}
			while (nums.containsKey(right)) {
				nums.put(right, false);
				currentLength++;
				right++;
			}
			if (currentLength > longestLength) {
				longestLength = currentLength;
				bestRange = new int[] { left + 1, right - 1 };
			}
		}
		return bestRange;
	}

	public static void main(String[] args) {
		int[] arr = largestRange(new int[] { 1, 11, 3, 0, 15, 5, 2, 4, 10, 7, 12, 6 });
		System.out.println(Arrays.toString(arr));
	}

}
