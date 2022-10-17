package com.govind.alogexpert.easy;

import java.util.Arrays;

public class SortedSequareArray {

	public static int[] sortedSquaredArraySolution1(int[] array,int  n) {

		int[] result = new int[array.length];
		for (int i = 0; i < array.length; i++) {
			int sum=1;
			for(int j=0;j<n;j++) {
			   sum *= array[i] ;
			}
			result[i]=sum;
		}
		Arrays.sort(result);
		return result;
	}

	public static int[] sortedSquaredArraySolution2(int[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = array[i] * array[i];
		}
		Arrays.sort(array);
		return array;

	}

	public static int[] sortedSquaredArraySolution3(int[] array) {
		int[] result = new int[array.length];
		int smallValueIdx = 0;
		int largestValueIdx = array.length - 1;
		for (int idx = array.length-1; idx > 0; idx--) {
            int small=array[smallValueIdx];
            int large=array[largestValueIdx];
			if(Math.abs(small)>Math.abs(large)) {
				result[idx]=small*small;
				smallValueIdx++;
			}else {
				result[idx]=large*large;
				largestValueIdx--;
			}
		}
		return result;

	}

	public static void main(String[] args) {
		int[] input = new int[] {1,2,3};//{ -10, -5, 0, 5, 10 };
		System.out.println(Arrays.toString(sortedSquaredArraySolution1(input,3)));

	}

}
