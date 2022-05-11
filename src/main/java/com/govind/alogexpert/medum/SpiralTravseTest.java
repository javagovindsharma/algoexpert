package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.List;

public class SpiralTravseTest {
	public static List<Integer> spiralTraverse(int[][] array) {
		if (array.length == 0)
			return new ArrayList<Integer>();
		ArrayList<Integer> result = new ArrayList<Integer>();
		spiralFill(array, 0, array.length-1, 0, array[0].length-1, result);
		return result;
	}

	public static void spiralFill(int[][] array, int startRow, int endRow, int startCol, int endCol,
			ArrayList<Integer> result) {

		    if(startRow>endRow || startCol>endCol) {
		    	  return;
		    }
			for (int col = startCol; col <= endCol; col++) {
				result.add(array[startRow][col]);
			}
			for (int row = startRow + 1; row <= endRow; row++) {
				result.add(array[row][endCol]);
			}
			for (int col = endCol - 1; col >= startCol; col--) {
				if (startRow == endRow)
					break;
				result.add(array[endRow][col]);
			}
			for (int row = endRow - 1; row >= startRow+1; row--) {
				if (startCol == endCol)
					break;
				result.add(array[row][startCol]);
			}
	   spiralFill(array, startRow+1, endRow-1, startCol+1, endCol-1, result);	
	}

	public static List<Integer> spiralTraverse1(int[][] array) {

		int startRow = 0;
		int endRow = array.length - 1;
		int startCol = 0;
		int endCol = array[0].length - 1;
		ArrayList<Integer> result = new ArrayList<Integer>();
		while (startRow <= endRow && startCol <= endCol) {
			for (int col = startCol; col <= endCol; col++) {
				result.add(array[startRow][col]);
			}
			for (int row = startRow + 1; row <= endRow; row++) {
				result.add(array[row][endCol]);
			}
			for (int col = endCol - 1; col >= startCol; col--) {
				if (startRow == endRow)
					break;
				result.add(array[endRow][col]);
			}
			for (int row = endRow - 1; row > startRow; row--) {
				if (startCol == endCol)
					break;
				result.add(array[row][startCol]);
			}
			startCol++;
			startRow++;
			endCol--;
			endRow--;

		}
		return result;
	}

	public static void main(String[] args) {
		int[][] arr = new int[][] { { 1, 2, 3, 4 }, { 12, 13, 14, 5 }, { 11, 16, 15, 6 }, { 10, 9, 8, 7 }, };
		System.out.println(SpiralTravseTest.spiralTraverse(arr).toString());
	}

}
