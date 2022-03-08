package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TreeNumberSum {

	public List<Integer[]> threeNumberSum(int[] array, int targetSum) {
		List<Integer[]> mainlist = new ArrayList<Integer[]>();
		for (int idx1 = 0; idx1 < array.length; idx1++) {
			int firstValue = array[idx1];
			for (int idx2 =idx1+1 ; idx2 < array.length; idx2++) {
				int secondValue = array[idx2];
				for (int idx3 =idx2+1 ; idx3 < array.length; idx3++) {
					int thirdValue = array[idx3];
					if (firstValue + secondValue+thirdValue == targetSum) {
						Integer[] arr=new Integer[] { firstValue, secondValue ,thirdValue};
						mainlist.add(arr);
					}
				}
           }
		}
	   return mainlist;

	}

	public static void main(String[] args) {
		int[] array = new int[] { 12, 3, 1, 2, -6, 5, -8, 6 };
		int targetSum = 0;
		new TreeNumberSum().threeNumberSum(array, targetSum);
	}

}
