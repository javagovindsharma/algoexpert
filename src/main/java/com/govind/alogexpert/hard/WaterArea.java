package com.govind.alogexpert.hard;

public class WaterArea {

	// solution 1
	public static int waterArea1(int[] heights) {
		int[] maxes = new int[heights.length];
		int leftMax = 0;
		for (int i = 0; i < heights.length; i++) {
			int height = heights[i];
			maxes[i] = leftMax;
			leftMax = Math.min(leftMax, height);
		}

		int rightMax = 0;
		for (int i = heights.length - 1; i >= 0; i--) {
			int height = heights[i];
			int minHeight = Math.max(rightMax, maxes[i]);
			if (height < minHeight) {
				maxes[i] = minHeight - height;
			} else {
				maxes[i] = 0;
			}
			rightMax = Math.max(rightMax, height);
		}
		int total = 0;
		for (int i = 0; i < heights.length; i++) {
			total += maxes[i];
		}
		return total;
	}

	// solution 2
	public static int waterArea(int[] heights) {
		if (heights.length == 0)
			return 0;

		int leftIdx = 0;
		int rightIdx = heights.length - 1;
		int leftMax = heights[leftIdx];
		int rightMax = heights[rightIdx];
		int surfaceArea = 0;

		while (leftIdx < rightIdx) {
			if (heights[leftIdx] < heights[rightIdx]) {
				leftIdx++;
				leftMax = Math.max(leftMax, heights[leftIdx]);
				surfaceArea += leftMax - heights[leftIdx];
			} else {
				rightIdx--;
				rightMax = Math.max(rightMax, heights[rightIdx]);
				surfaceArea += rightMax - heights[rightIdx];
			}
		}
		return surfaceArea;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
