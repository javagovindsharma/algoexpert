package com.govind.alogexpert.easy;

import java.util.*;

public class ValidateSequence {

	public static void main(String[] args) {
		List<Integer> array = Arrays.asList(5, 1, 22, 25, 6, -1, 8, 10);// [5, 1, 22, 25, 6, -1, 8, 10],
		List<Integer> sequence = Arrays.asList(1, 6, -1, 5);// 1, 6, 1, -1, 10);
		System.out.println(isValidSubsequenceSolution3(array, sequence));
	}

	public static boolean isValidSubsequenceSolution1(List<Integer> array, List<Integer> sequence) {
		int sqInt = 0, arInt = 0;
		while (sqInt < sequence.size() && arInt < array.size()) {

			if (sequence.get(sqInt).equals(array.get(arInt))) {
				sqInt++;
			}
			arInt++;
		}
		return sqInt == sequence.size();
	}

	public static boolean isValidSubsequenceSolution2(List<Integer> array, List<Integer> sequence) {
		int sqInt = 0;
		for (Integer val : array) {
			
			if (sqInt == sequence.size()) {
				System.out.println(sqInt+"  "+val);
				break;
			}
			if (sequence.get(sqInt).equals(val)) {
				System.out.println(sqInt+"  "+sequence.get(sqInt)+"  "+val);
				sqInt++;
			}
		}
		
		return sqInt == sequence.size();
	}

	public static boolean isValidSubsequenceSolution3(List<Integer> array, List<Integer> sequence) {
		int sqInt = 0;
		for (Integer val : array) {
			System.out.println(val);
		}
		return sqInt == sequence.size();
	}
}
