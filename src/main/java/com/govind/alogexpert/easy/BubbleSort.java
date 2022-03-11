package com.govind.alogexpert.easy;

import java.util.Arrays;

public class BubbleSort {

	public static int[] bubbleSort(int[] array) {
		if (array.length == 0)
			return new int[] {};
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length-1; j++) {
                if(array[j+1]<array[j]) {
                	int temp=array[j+1];
                	array[j+1]=array[j];
                	array[j]=temp;
                }
			}
		}
		return array;

	}

	public static void main(String[] args) {
		int[] array = new int[] { 8, 5, 2, 9, 5, 6, 3 };
		System.out.println(Arrays.toString(bubbleSort(array)));
	}

}
