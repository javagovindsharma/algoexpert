package com.govind.alogexpert.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class NonContructibleChange {
	static Set<Integer> setValue = new HashSet<>();

	public static void sumofArray(int[] arr, int index, ArrayList<Integer> path) {

		if (index == arr.length) {
			if (path.size() > 0)
				setValue.add(path.stream().reduce(0, Integer::sum));
		} else {
			sumofArray(arr, index + 1, path);
			path.add(arr[index]);
			sumofArray(arr, index + 1, path);
			path.remove(path.size() - 1);
		}
		return;
	}

	public static int sumofArray1(int[] array) {

		Arrays.sort(array);
		int currentChangeValue=0;
		for(int coin:array) {
			if(coin>currentChangeValue+1) {
				return currentChangeValue+=1;
			}
			currentChangeValue+=coin;
		}
		return currentChangeValue+1;
		
		
	}

	
	public static void solution1() {
		int[] array = new int[] {109, 2000, 8765, 19, 18, 17, 16, 8, 1, 1, 2, 4}; 
		//int[] array = new int[]{6, 4, 5, 1, 1, 8, 9}; 
		//int[] array = new int[]{1, 1, 1, 1, 1}; 
		//int[] array = new int[]{ 5, 7, 1, 1, 2, 3, 22 };
		//int[] array = new int[]{109};
		ArrayList<Integer> path = new ArrayList<>();
		sumofArray(array, 0, path);
		System.out.println(setValue);
		ArrayList<Integer> list = new ArrayList<Integer>(setValue);
		setValue.clear();
		Collections.sort(list);
		System.err.println(list);

		int maxValue = list.get(list.size() - 1);
		int result = 0;
		for (int i = 1; i < maxValue; i++) {
			int val = list.get(i - 1);
			//System.out.println(i + "  " + val);
			if (i != val) {
				result = i;
				break;
			}
		}
		if(result==0) {
			result=maxValue+1;
		}
		System.out.println("result" + result);
	}
	public static void main(String[] args) {
		//solution1();
		int[] array = new int[] {109, 2000, 8765, 19, 18, 17, 16, 8, 1, 1, 2, 4}; 
		System.out.println(sumofArray1(array));

	}

}
