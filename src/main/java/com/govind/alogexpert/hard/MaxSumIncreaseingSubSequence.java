package com.govind.alogexpert.hard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxSumIncreaseingSubSequence {
	public static List<List<Integer>> maxSumIncreasingSubsequence(int[] array) {
		int[] sequences = new int[array.length];
		Arrays.fill(sequences, Integer.MIN_VALUE);
		int[] sums = array.clone();
		int maxSumIdx = 0;
		for (int i = 0; i < array.length; i++) {
			int currentNum = array[i];
			for (int j = 0; j < i; j++) {
				int otherNum = array[j];
				if (otherNum < currentNum && sums[j] + currentNum >= sums[i]) {
					sums[i] = sums[j] + currentNum;
					sequences[i] = j;
				}
			}
			if (sums[i] >= sums[maxSumIdx]) {
				maxSumIdx = i;
			}
		}
		return buildSequence(array, sequences, maxSumIdx, sums[maxSumIdx]);
	}

	private static List<List<Integer>> buildSequence(int[] array, int[] sequences, int currentIdx, int sums) {
		List<List<Integer>> sequence = new ArrayList<List<Integer>>();
		sequence.add(new ArrayList<Integer>());
		sequence.add(new ArrayList<Integer>());
		sequence.get(0).add(sums);

		while (currentIdx != Integer.MIN_VALUE) {
			sequence.get(1).add(0, array[currentIdx]);
			currentIdx = sequences[currentIdx];
		}
		return sequence;
	}

	public static void main(String[] args) {
		int[] input = { 10, 70, 20, 30, 50, 11, 30 };
		int[][] expected = { { 110 }, { 10, 20, 30, 50 } };
		assertTrue(compare(MaxSumIncreaseingSubSequence.maxSumIncreasingSubsequence(input), expected));
	}

	public static boolean compare(List<List<Integer>> arr1, int[][] arr2) {
		if (arr1.get(0).get(0) != arr2[0][0]) {
			return false;
		}
		for (int i = 0; i < arr1.get(1).size(); i++) {
			if (arr1.get(1).get(i) != arr2[1][i]) {
				return false;
			}
		}
		return true;
	}

}
