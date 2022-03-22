package com.govind.alogexpert.medum;

public class MonotonicArray {

	public static boolean isMonotonic(int[] array) {
		if (array.length < 2) {
			return true;
		}
		int previous = array[0];
		boolean flag = false;
		for (int i = 1; i < array.length; i++) {
			int current = array[i];
			if (previous > current) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}

	public static void main(String[] args) {
		int[] array = new int[] { 1, 5, 10, 1100, 1101, 1102, 9001 };
		System.out.println(isMonotonic(array));

	}

}
