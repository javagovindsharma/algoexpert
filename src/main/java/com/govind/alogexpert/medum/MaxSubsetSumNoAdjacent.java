package com.govind.alogexpert.medum;

public class MaxSubsetSumNoAdjacent {
	public static int maxSubsetSumNoAdjacent(int[] array) {
		if (array.length == 0) {
			return 0;
		} else if (array.length == 1) {
			return array[0];
		}
		int second =array[0];
		int first =Math.max(array[1], array[0]);
		for (int i = 2; i < array.length; i++) {
			int current = Math.max(first,second + array[i]);
			second=first;
			first=current;
			
		}
		return first;
	}
	public static int maxSubsetSumNoAdjacent1(int[] array) {
		if (array.length == 0) {
			return 0;
		} else if (array.length == 1) {
			return array[0];
		}
		int[] maxSum = array.clone();
		maxSum[1] = Math.max(array[0], array[1]);
		for (int i = 2; i < array.length; i++) {
			maxSum[i] = Math.max(maxSum[i - 1], maxSum[i - 2] + array[i]);
		}
		return maxSum[array.length - 1];
	}

	public static void main(String[] args) {
		
		int[] input = {75, 105, 120, 75, 90, 135};
		System.out.println(MaxSubsetSumNoAdjacent.maxSubsetSumNoAdjacent(input));
		//== 330);

	}

}
