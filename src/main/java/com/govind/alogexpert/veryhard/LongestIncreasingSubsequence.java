package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestIncreasingSubsequence {

	// solution 1
	public static List<Integer> longestIncreasingSubsequence(int[] array) {
		int[] sequences = new int[array.length];
		int[] indices = new int[array.length+1];
		Arrays.fill(indices, Integer.MIN_VALUE);
		int length=0;
		
		for(int i=0;i<array.length;i++) {
			int num=array[i];
			int newLength=binarySearch(1,length,indices,array,num);
			sequences[i]=indices[newLength-1];
			indices[newLength]=i;
			length=Math.max(length,newLength);
		}
		return buildSequence(array, sequences,indices[length]);
	}

	private static int binarySearch(int startIdx, int endIdx, int[] indices, int[] array, int num) {
		if(startIdx>endIdx) {
			return startIdx;
		}
		int middleIdx=(startIdx+endIdx)/2;
		if(array[indices[middleIdx]]<num) {
			startIdx=middleIdx+1;			
		}else {
			endIdx=middleIdx-1;
		}
		return binarySearch(startIdx, endIdx, indices, array, num);
	}

	// solution 2
	public static List<Integer> longestIncreasingSubsequence1(int[] array) {
		int[] sequence = new int[array.length];
		Arrays.fill(sequence, Integer.MIN_VALUE);
		int[] lengths = new int[array.length];
		Arrays.fill(lengths, 1);
		int maxLengthIdx = 0;
		for (int i = 0; i < array.length; i++) {
			int currentNum = array[i];
			for (int j = 0; j < i; j++) {
				int otherNum = array[j];
				if (otherNum < currentNum && lengths[j] + 1 > lengths[i]) {
					lengths[i] = lengths[j] + 1;
					sequence[i] = j;
				}
			}
			if (lengths[i] > lengths[maxLengthIdx]) {
				maxLengthIdx = i;
			}
		}
		return buildSequence(array, sequence, maxLengthIdx);
	}

	private static List<Integer> buildSequence(int[] array, int[] sequences, int currentIdx) {
		List<Integer> sequence = new ArrayList<>();
		while (currentIdx != Integer.MIN_VALUE) {
			sequence.add(0, array[currentIdx]);
			currentIdx = sequences[currentIdx];
		}
		return sequence;
	}

	public static void main(String[] args) {
		int[] expected = { -24, 2, 3, 5, 6, 35 };
		assertTrue(compare(LongestIncreasingSubsequence
				.longestIncreasingSubsequence(new int[] { 5, 7, -24, 12, 10, 2, 3, 12, 5, 6, 35 }), expected));

	}

	public static boolean compare(List<Integer> arr1, int[] arr2) {
		if (arr1.size() != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.size(); i++) {
			if (arr1.get(i) != arr2[i]) {
				return false;
			}
		}
		return true;
	}

}
