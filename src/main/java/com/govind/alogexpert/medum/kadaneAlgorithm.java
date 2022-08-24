package com.govind.alogexpert.medum;

public class kadaneAlgorithm {
	

	public static int kadanesAlgorithm(int[] array) {
		int maxEndingHere=array[0];
		int maxSoFar=array[0];
		for(int i=1;i<array.length;i++) {
			int num=array[i];
			maxEndingHere=Math.max(num,maxEndingHere+num);
			maxSoFar=Math.max(maxSoFar, maxEndingHere);
		}
		return maxSoFar;
	}

	public static void main(String[] args) {
		int[] input = { 3, 5, -9, 1, 3, -2, 3, 4, 7, 2, -9, 6, 3, 1, -5, 4 };
		System.out.println(input + "==" + 19);
	}

}
