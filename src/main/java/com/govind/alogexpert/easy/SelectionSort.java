package com.govind.alogexpert.easy;

import java.util.Arrays;

public class SelectionSort {
	public static int[] selectionSort(int[] array) {
		int startIdx=0;
		if(array.length==0) {
			return new int[] {};
		}
		while(startIdx<array.length-1) {
			int smallestIdx=startIdx;
			for(int i=startIdx+1;i<array.length;i++) {
				 if(array[smallestIdx]>array[i]) {
					 smallestIdx=i;
				 }
			 }
			swap(startIdx,smallestIdx,array);
			startIdx++;
		}
		return array;
	}

	private static void swap(int i, int j, int[] array) {
		int temp=array[i];
		array[i]=array[j];
		array[j]=temp;
		
	}

	public static void main(String[] args) {
	    int[] arr=new int[] {8, 5, 2, 9, 5, 6, 3};
		System.out.println(Arrays.toString(selectionSort(arr)));

	}

}
