package com.govind.alogexpert.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SameBST {
	// solution 2
	public static boolean sameBsts(List<Integer> arrayOne, List<Integer> arrayTwo) {
		return areSameBsts(arrayOne, arrayTwo, 0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static boolean areSameBsts(List<Integer> arrayOne, List<Integer> arrayTwo, int rootIdxOne, int rootIdxTwo,
			int minValue, int maxValue) {
		if (rootIdxOne == -1 || rootIdxTwo == -1)
			return rootIdxOne == rootIdxTwo;
		if (arrayOne.get(rootIdxOne).intValue() != arrayTwo.get(rootIdxTwo).intValue())
			return false;

		int leftRootIdxOne = getIdxOfFirstSmaller(arrayOne, rootIdxOne, minValue);
		int leftRootIdxTwo = getIdxOfFirstSmaller(arrayTwo, rootIdxTwo, minValue);
		int rightRootIdxOne = getIdxOfFirstBiggerOrEqual(arrayOne, rootIdxOne, maxValue);
		int rightRootIdxTwo = getIdxOfFirstBiggerOrEqual(arrayTwo, rootIdxTwo, maxValue);

		int currentValue = arrayOne.get(rootIdxOne);

		boolean leftAreSame = areSameBsts(arrayOne, arrayTwo, leftRootIdxOne, leftRootIdxTwo, minValue, currentValue);
		boolean rightAreSame = areSameBsts(arrayOne, arrayTwo, rightRootIdxOne, rightRootIdxTwo, currentValue,
				maxValue);
		return leftAreSame && rightAreSame;
	}

	private static int getIdxOfFirstSmaller(List<Integer> array, int startingIdx, int minValue) {
		for (int i = startingIdx + 1; i < array.size(); i++) {
			if (array.get(i).intValue() < array.get(startingIdx).intValue() && array.get(i).intValue() >= minValue)
				return i;
		}
		return -1;
	}

	private static int getIdxOfFirstBiggerOrEqual(List<Integer> array, int startingIdx, int maxValue) {
		for (int i = startingIdx + 1; i < array.size(); i++) {
			if (array.get(i).intValue() >= array.get(startingIdx).intValue() && array.get(i).intValue() < maxValue)
				return i;
		}
		return -1;
	}

	// solution 1
	public static boolean sameBsts1(List<Integer> arrayOne, List<Integer> arrayTwo) {
		if (arrayOne.size() != arrayTwo.size())
			return false;
		if (arrayOne.size() == 0 && arrayTwo.size() == 0)
			return true;
		if (arrayOne.get(0).intValue() != arrayTwo.get(0).intValue())
			return false;

		List<Integer> leftOne = getSmaller(arrayOne);
		List<Integer> leftTwo = getSmaller(arrayTwo);
		List<Integer> rightOne = getBiggerOrEqual(arrayOne);
		List<Integer> rightTwo = getBiggerOrEqual(arrayTwo);

		return sameBsts(leftOne, leftTwo) && sameBsts(rightOne, rightTwo);
	}

	private static List<Integer> getSmaller(List<Integer> array) {
		List<Integer> smaller = new ArrayList<Integer>();
		for (int i = 1; i < array.size(); i++) {
			if (array.get(i).intValue() < array.get(0).intValue())
				smaller.add(array.get(i));
		}
		return smaller;
	}

	private static List<Integer> getBiggerOrEqual(List<Integer> array) {
		List<Integer> biggerOrEqual = new ArrayList<Integer>();
		for (int i = 1; i < array.size(); i++) {
			if (array.get(i).intValue() >= array.get(0).intValue())
				biggerOrEqual.add(array.get(i));
		}
		return biggerOrEqual;
	}

	public static void main(String[] args) {
		List<Integer> arrayOne = new ArrayList<Integer>(Arrays.asList(10, 15, 8, 12, 94, 81, 5, 2, 11));
		List<Integer> arrayTwo = new ArrayList<Integer>(Arrays.asList(10, 8, 5, 15, 2, 12, 11, 94, 81));
		// JUnit4.assertTrue(SameBST.sameBsts(arrayOne, arrayTwo) == true);
	}

}
