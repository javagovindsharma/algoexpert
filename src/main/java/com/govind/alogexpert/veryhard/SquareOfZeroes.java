package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SquareOfZeroes {

	// solution 4
	public static boolean squareOfZeroes(List<List<Integer>> matrix) {
		List<List<InfoMatrixItem>> infoMatrix = preComputedNumOfZeroes(matrix);
		int n = matrix.size();
		for (int topRow = 0; topRow < n; topRow++) {
			for (int leftCol = 0; leftCol < n; leftCol++) {
			  int squarelength=2;
			  while(squarelength<=n-leftCol && squarelength<=n-topRow) {
				  int bottomRow=topRow+squarelength-1;
				  int rightCol=leftCol+squarelength-1;
				  if(isSquareOfZeroes(infoMatrix, topRow,leftCol,bottomRow, rightCol)) return true;
				  squarelength++;
				  
			  }
			}
		}

		return true;
	}

	// solution 3
	public static boolean squareOfZeroes3(List<List<Integer>> matrix) {
		List<List<InfoMatrixItem>> infoMatrix = preComputedNumOfZeroes(matrix);
		int lastIdx = matrix.size() - 1;
		Map<String, Boolean> cache = new HashMap<>();
		return hasSquareOfZeroes(infoMatrix, 0, 0, lastIdx, lastIdx, cache);
	}

	private static List<List<InfoMatrixItem>> preComputedNumOfZeroes(List<List<Integer>> matrix) {
		List<List<InfoMatrixItem>> infoMatrix = new ArrayList<List<InfoMatrixItem>>();

		for (int i = 0; i < matrix.size(); i++) {
			List<InfoMatrixItem> inner = new ArrayList<InfoMatrixItem>();
			for (int j = 0; j < matrix.get(i).size(); j++) {
				int numZeroes = matrix.get(i).get(j) == 0 ? 1 : 0;
				inner.add(new InfoMatrixItem(numZeroes, numZeroes));
			}
			infoMatrix.add(inner);
		}

		int lastIdx = matrix.size() - 1;
		for (int row = lastIdx; row >= 0; row--) {
			for (int col = lastIdx; col >= 0; col--) {
				if (matrix.get(row).get(col) == 1)
					continue;
				if (row < lastIdx) {
					infoMatrix.get(row).get(col).numZeroesBelow += infoMatrix.get(row + 1).get(col).numZeroesBelow;
				}
				if (col < lastIdx) {
					infoMatrix.get(row).get(col).numZeroesRight += infoMatrix.get(row).get(col + 1).numZeroesRight;

				}
			}
		}

		return infoMatrix;
	}

	private static boolean hasSquareOfZeroes(List<List<InfoMatrixItem>> matrix, int r1, int c1, int r2, int c2,
			Map<String, Boolean> cache) {
		if (r1 >= r2 || c1 >= c2)
			return false;

		String key = String.valueOf(r1) + '_' + String.valueOf(c1) + '_' + String.valueOf(r2) + '_'
				+ String.valueOf(c2);

		if (cache.containsKey(key))
			return cache.get(key);

		cache.put(key,
				isSquareOfZeroes(matrix, r1, c1, r2, c2)
						|| hasSquareOfZeroes(matrix, r1 + 1, c1 + 1, r2 - 1, c2 - 1, cache)
						|| hasSquareOfZeroes(matrix, r1, c1 + 1, r2 - 1, c2, cache)
						|| hasSquareOfZeroes(matrix, r1 + 1, c1, r2, c2 - 1, cache)
						|| hasSquareOfZeroes(matrix, r1 + 1, c1 + 1, r2, c2, cache)
						|| hasSquareOfZeroes(matrix, r1, c1, r2 - 1, c2 - 1, cache));
		return cache.get(key);
	}

	private static boolean isSquareOfZeroes(List<List<InfoMatrixItem>> infoMatrix, int r1, int c1, int r2, int c2) {
		int squareLength = c2 - c1 + 1;
		boolean hasTopBorder = infoMatrix.get(r1).get(c1).numZeroesRight >= squareLength;
		boolean hasLeftBorder = infoMatrix.get(r1).get(c1).numZeroesBelow >= squareLength;
		boolean hasTBottomBorder = infoMatrix.get(r2).get(c1).numZeroesRight >= squareLength;
		boolean hasRightBorder = infoMatrix.get(r1).get(c2).numZeroesBelow >= squareLength;
		return hasTopBorder && hasLeftBorder && hasTBottomBorder && hasRightBorder;
	}

	static class InfoMatrixItem {
		int numZeroesBelow;
		int numZeroesRight;

		public InfoMatrixItem(int numZeroesBelow, int numZeroesRight) {
			super();
			this.numZeroesBelow = numZeroesBelow;
			this.numZeroesRight = numZeroesRight;
		}

	}

	// solution 2
	public static boolean squareOfZeroes2(List<List<Integer>> matrix) {
		int n = matrix.size();

		for (int topRow = 0; topRow < n; topRow++) {
			for (int leftCol = 0; leftCol < n; leftCol++) {
				int squareLength = 2;
				while (squareLength <= n - leftCol && squareLength <= n - topRow) {
					int bottomRow = topRow + squareLength - 1;
					int rightCol = leftCol + squareLength - 1;
					if (isSquareOfZeroes1(matrix, topRow, leftCol, bottomRow, rightCol)) {
						return true;
					}
					squareLength++;
				}
			}
		}
		return false;
	}

	// solution 1
	public static boolean squareOfZeroes1(List<List<Integer>> matrix) {
		int lastIdx = matrix.size() - 1;
		Map<String, Boolean> cache = new HashMap<String, Boolean>();
		return hasSquareOfZeroes1(matrix, 0, 0, lastIdx, lastIdx, cache);
	}

	private static boolean hasSquareOfZeroes1(List<List<Integer>> matrix, int r1, int c1, int r2, int c2,
			Map<String, Boolean> cache) {
		if (r1 >= r2 || c1 >= c2)
			return false;

		String key = String.valueOf(r1) + '_' + String.valueOf(c1) + '_' + String.valueOf(r2) + '_'
				+ String.valueOf(c2);

		if (cache.containsKey(key))
			return cache.get(key);

		cache.put(key,
				isSquareOfZeroes1(matrix, r1, c1, r2, c2)
						|| hasSquareOfZeroes1(matrix, r1 + 1, c1 + 1, r2 - 1, c2 - 1, cache)
						|| hasSquareOfZeroes1(matrix, r1, c1 + 1, r2 - 1, c2, cache)
						|| hasSquareOfZeroes1(matrix, r1 + 1, c1, r2, c2 - 1, cache)
						|| hasSquareOfZeroes1(matrix, r1 + 1, c1 + 1, r2, c2, cache)
						|| hasSquareOfZeroes1(matrix, r1, c1, r2 - 1, c2 - 1, cache));
		return cache.get(key);
	}

	private static boolean isSquareOfZeroes1(List<List<Integer>> matrix, int r1, int c1, int r2, int c2) {
		for (int row = r1; row < r2 + 1; row++) {
			if (matrix.get(row).get(c1) != 0 || matrix.get(row).get(c2) != 0)
				return false;
		}
		for (int col = c1; col < c2 + 1; col++) {
			if (matrix.get(r1).get(col) != 0 || matrix.get(r2).get(col) != 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		List<List<Integer>> test = new ArrayList<List<Integer>>();
		test.add(new ArrayList<Integer>(Arrays.asList(new Integer[] { 1, 1, 1, 0, 1, 0 })));
		test.add(new ArrayList<Integer>(Arrays.asList(new Integer[] { 0, 0, 0, 0, 0, 1 })));
		test.add(new ArrayList<Integer>(Arrays.asList(new Integer[] { 0, 1, 1, 1, 0, 1 })));
		test.add(new ArrayList<Integer>(Arrays.asList(new Integer[] { 0, 0, 0, 1, 0, 1 })));
		test.add(new ArrayList<Integer>(Arrays.asList(new Integer[] { 0, 1, 1, 1, 0, 1 })));
		test.add(new ArrayList<Integer>(Arrays.asList(new Integer[] { 0, 0, 0, 0, 0, 1 })));
		assertTrue(SquareOfZeroes.squareOfZeroes(test));

	}

}
