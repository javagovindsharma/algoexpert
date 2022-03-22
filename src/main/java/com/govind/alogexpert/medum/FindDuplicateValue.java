package com.govind.alogexpert.medum;

import java.util.HashSet;

public class FindDuplicateValue {
	public static int firstDuplicateValue1(int[] array) {

		for (int value : array) {
			int absValue = Math.abs(value);
			{
				if (array[absValue - 1] < 0)
					return absValue;
			}
			array[absValue - 1] *= -1;
		}
		return -1;

	}

	public static int firstDuplicateValue2(int[] array) {

		HashSet<Integer> hset=new HashSet<Integer>();
		for(int value:array) {
			if(hset.contains(value)) {
				return value;
			}
			hset.add(value);
		}
		return -1;

	}

	public static int firstDuplicateValue(int[] array) {
		int minSecondIndex = array.length;
		for (int i = 0; i < array.length; i++) {
			int value = array[i];
			for (int j = i + 1; j < array.length; j++) {
				int valueToCompare = array[j];
				if (value == valueToCompare) {
					minSecondIndex = Math.min(minSecondIndex, j);
				}
			}
		}
		if (minSecondIndex == array.length) {
			return -1;
		}
		return array[minSecondIndex];
	}

	public static void main(String[] args) {
		int[] array = new int[] { 2, 1, 5, 2, 3, 3, 4 };
		System.out.println(firstDuplicateValue2(array));
	}

}
