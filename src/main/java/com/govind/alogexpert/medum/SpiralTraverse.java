package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SpiralTraverse {
	public static List<Integer> spiralTraverse(int[][] array) {
		List<Integer> list = new ArrayList<Integer>();
		int startRow=0;
		int startCol=0;
		while(startRow<array.length) {
			while(startCol<array[startRow].length) {
				list.add(array[startRow][startCol]);
				startCol++;
			}
		}
		
		Collections.sort(list);
		return list;
	}

	public static void main(String[] args) {
		int array[][] = { { 1, 2, 3, 4 }, { 12, 13, 14, 5 }, { 11, 16, 15, 6 }, { 10, 9, 8, 7 } };
		System.out.println(spiralTraverse(array));
	}

}
