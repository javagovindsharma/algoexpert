package com.govind.alogexpert.easy;

import java.util.Arrays;

public class InsertSort {
	
	public static int[] insertionSort(int[] array) {
		int j=0,temp=0;
		for(int i=0;i<array.length;i++) {
			temp=array[i];
			j=i-1;
			while(j>=0 && temp<=array[j]) {
				array[j+1]=array[j];
				j--;
			}
			array[j+1]=temp;
			
		}
	    return array;
	  }

	public static void main(String[] args) {
		int[] array=new int[]{8, 5, 2, 9, 5, 6, 3};
		System.out.println(Arrays.toString(insertionSort(array)));

	}

}
