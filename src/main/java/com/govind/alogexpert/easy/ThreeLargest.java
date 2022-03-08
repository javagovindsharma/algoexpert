package com.govind.alogexpert.easy;
// Java code to find largest three elements

import java.util.Arrays;

// in an array

public class ThreeLargest {
	static int[] print3largest(int arr[], int arr_size) {
		int[] array = new int[3];
		int i, first, second, third;
		if (arr_size < 3) {
			System.out.print(" Invalid Input ");
			return array;
		}

		third = first = second = Integer.MIN_VALUE;
		for (i = 0; i < arr_size; i++) {
			if (arr[i] > first) {
				third = second;
				second = first;
				first = arr[i];
			} else if (arr[i] > second) {
				third = second;
				second = arr[i];
			}

			else if (arr[i] > third)
				third = arr[i];
		}

		System.out.println("Three largest elements are " + first + " " + second + " " + third);
		return array;
	}

	public static int[] findThreeLargestNumbers1(int[] array) {
		int[] arr = new int[3];
		int first, second, third;
		first = array[0];
		second = array[1];
		third = array[2];
		for (int i = 0; i < array.length; i++) {
			if (array[i] >= first && array[i] > second && array[i] > third) {
				first = array[i];

			}
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i] >= second && array[i] > third && array[i] > first) {
				second = array[i];

			}
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i] >= third && array[i] > first && array[i] > second) {
				third = array[i];
			}
		}
		if (third > second) {
			int t = third;
			third = second;
			second = t;
		}
		arr[2] = first;
		arr[1] = second;
		arr[0] = third;
		return arr;
	}

	/**
	 * third
	 * 
	 * @param args
	 */

	public static int[] findThreeLargestNumbers(int[] array) {
		int[] threeLargest = { Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE };
		for (int num : array) {
			updateLargest(threeLargest, num);
		}
		return threeLargest;
	}

	private static void updateLargest(int[] threeLargest, int num) {
		if (num > threeLargest[2]) {
			siftAndupdate(threeLargest, num, 2);
		} else if (num > threeLargest[1]) {
			siftAndupdate(threeLargest, num, 1);
		} else if (num > threeLargest[0]) {
			siftAndupdate(threeLargest, num, 0);
		}

	}

	private static void siftAndupdate(int[] array, int num, int idx) {
		for (int i = 0; i <= idx; i++) {
			if (i == idx) {
				array[i] = num;
			} else {
				array[i] = array[i + 1];
			}
		}

	}

	public static void main(String[] args) {
		int arr[] = { 55, 7, 8 };// { 141, 1, 17, -7, -17, -27, 18, 541, 8, 7, 7 };
		int n = arr.length;
		System.out.println(Arrays.toString(findThreeLargestNumbers(arr)));
	}
}
