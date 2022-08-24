package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.Stack;

public class RemoveInslands {

	// solution 1
	public int[][] removeIslands(int[][] matrix) {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				boolean rowIsBorder = row == 0 || row == matrix.length - 1;
				boolean colIsBorder = col == 0 || col == matrix[row].length - 1;
				boolean isBorder = rowIsBorder || colIsBorder;

				if (!isBorder) {
					continue;
				}
				if (matrix[row][col] != 1) {
					continue;
				}
				changeOnesConnectedToBorder(matrix, row, col);
			}
		}

		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				int color = matrix[row][col];
				if (color == 1) {
					matrix[row][col] = 0;
				} else if (color == 2) {
					matrix[row][col] = 1;
				}
			}
		}
		return matrix;
	}

	public void changeOnesConnectedToBorder(int[][] matrix, int startRow, int startCol) {
		Stack<int[]> stack = new Stack<int[]>();
		stack.push(new int[] { startRow, startCol });

		while (stack.size() > 0) {
			int[] currentPosition = stack.pop();
			int currentRow = currentPosition[0];
			int currentCol = currentPosition[1];
            matrix[currentRow][currentCol]=2;
			
            int[][] neighbors = getNeighbors(matrix, currentRow, currentCol);
			for (int[] neighbor : neighbors) {
				int row = neighbor[0];
				int col = neighbor[1];

				if (matrix[row][col] != 1) {
					continue;
				}
				stack.push(neighbor);
			}
		}
	}
	// solution 2

	public int[][] removeIslands1(int[][] matrix) {

		boolean[][] onesConnectedToBorder = new boolean[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			onesConnectedToBorder[i][matrix[0].length - 1] = false;
		}
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[row].length; col++) {
				boolean rowIsBorder = row == 0 || row == matrix.length - 1;
				boolean colIsBorder = col == 0 || col == matrix[row].length - 1;
				boolean isBorder = rowIsBorder || colIsBorder;

				if (!isBorder) {
					continue;
				}
				if (matrix[row][col] != 1) {
					continue;
				}
				findOnesConnectedToBorder(matrix, row, col, onesConnectedToBorder);
			}
		}

		for (int row = 1; row < matrix.length - 1; row++) {
			for (int col = 1; col < matrix[row].length - 1; col++) {
				if (onesConnectedToBorder[row][col]) {
					continue;
				}
				matrix[row][col] = 0;
			}
		}
		return matrix;
	}

	public void findOnesConnectedToBorder(int[][] matrix, int startRow, int startCol,
			boolean[][] onesConnectedToBorder) {
		Stack<int[]> stack = new Stack<int[]>();
		stack.push(new int[] { startRow, startCol });

		while (stack.size() > 0) {
			int[] currentPosition = stack.pop();
			int currentRow = currentPosition[0];
			int currentCol = currentPosition[1];

			boolean alreadyVisited = onesConnectedToBorder[currentRow][currentCol];
			if (alreadyVisited) {
				continue;
			}

			onesConnectedToBorder[currentRow][currentCol] = true;
			int[][] neighbors = getNeighbors(matrix, currentRow, currentCol);
			for (int[] neighbor : neighbors) {
				int row = neighbor[0];
				int col = neighbor[1];

				if (matrix[row][col] != 1) {
					continue;
				}
				stack.push(neighbor);
			}
		}
	}

	public int[][] getNeighbors(int[][] matrix, int row, int col) {

		int numRows = matrix.length;
		int numCols = matrix[row].length;

		ArrayList<int[]> temp = new ArrayList<int[]>();

		if (row - 1 >= 0) {
			temp.add(new int[] { row - 1, col });// up
		}
		if (row + 1 < numRows) {
			temp.add(new int[] { row + 1, col });// Down
		}
		if (col - 1 >= 0) {
			temp.add(new int[] { row, col - 1 });// left
		}
		if (col + 1 < numCols) {
			temp.add(new int[] { row, col + 1 });// right
		}

		int[][] neighbors = new int[temp.size()][2];
		for (int i = 0; i < temp.size(); i++) {
			neighbors[i] = temp.get(i);
		}
		return neighbors;
	}

	public static void main(String[] args) {
		int[][] input = new int[][] { { 1, 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 1, 1 }, { 0, 0, 1, 0, 1, 0 },
				{ 1, 1, 0, 0, 1, 0 }, { 1, 0, 1, 1, 0, 0 }, { 1, 0, 0, 0, 0, 1 }, };
		int[][] expected = new int[][] { { 1, 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 1, 1 }, { 0, 0, 0, 0, 1, 0 },
				{ 1, 1, 0, 0, 1, 0 }, { 1, 0, 0, 0, 0, 0 }, { 1, 0, 0, 0, 0, 1 }, };
		int[][] actual = new RemoveInslands().removeIslands(input);
		for (int i = 0; i < actual.length; i++) {
			for (int j = 0; j < actual[i].length; j++) {
				System.out.println(actual[i][j] == expected[i][j]);
			}
		}

	}

}
