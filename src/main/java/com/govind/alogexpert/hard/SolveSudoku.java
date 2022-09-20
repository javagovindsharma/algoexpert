package com.govind.alogexpert.hard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

public class SolveSudoku {
	public ArrayList<ArrayList<Integer>> solveSudoku(ArrayList<ArrayList<Integer>> board) {
		solvePartialSudoku(0, 0, board);
		return board;
	}

	private boolean solvePartialSudoku(int row, int col, ArrayList<ArrayList<Integer>> board) {
		int currentRow = row;
		int currentCol = col;

		if (currentCol == board.get(currentRow).size()) {
			currentRow += 1;
			currentCol = 0;
			if (currentRow == board.size()) {
				return true;
			}
		}

		if (board.get(currentRow).get(currentCol) == 0) {
			return tryDigitAtPosition(currentRow, currentCol, board);
		}
		return solvePartialSudoku(currentRow, currentCol + 1, board);
	}

	private boolean tryDigitAtPosition(int row, int col, ArrayList<ArrayList<Integer>> board) {
		for (int digit = 1; digit < 10; digit++) {
			if (isValidAtPositions(digit, row, col, board)) {
				board.get(row).set(col, digit);
				if (solvePartialSudoku(row, col, board)) {
					return true;
				}
			}
		}
		board.get(row).set(col, 0);
		return false;
	}

	private boolean isValidAtPositions(int value, int row, int col, ArrayList<ArrayList<Integer>> board) {
		boolean rowIsValid = !board.get(row).contains(value);
		boolean columnIsValid = true;

		for (int r = 0; r < board.size(); r++) {
			if (board.get(r).get(col) == value)
				columnIsValid = false;
		}

		if (!rowIsValid || !columnIsValid) {
			return false;
		}

		// check subgrid constraints
		int subgridRowStart = (row / 3) * 3;
		int subgridColStart = (col / 3) * 3;

		for (int rowIdx = 0; rowIdx < 3; rowIdx++) {
			for (int colIdx = 0; colIdx < 3; colIdx++) {
				int rowToCheck = subgridRowStart + rowIdx;
				int colToCheck = subgridColStart + colIdx;
				int existingValue = board.get(rowToCheck).get(colToCheck);

				if (existingValue == value) {
					return false;
				}
			}
		}

		return true;
	}

	public static void main(String[] args) {
		int[][] inputValues = new int[][] { { 7, 8, 0, 4, 0, 0, 1, 2, 0 }, { 6, 0, 0, 0, 7, 5, 0, 0, 9 },
				{ 0, 0, 0, 6, 0, 1, 0, 7, 8 }, { 0, 0, 7, 0, 4, 0, 2, 6, 0 }, { 0, 0, 1, 0, 5, 0, 9, 3, 0 },
				{ 9, 0, 4, 0, 6, 0, 0, 0, 5 }, { 0, 7, 0, 3, 0, 0, 0, 1, 2 }, { 1, 2, 0, 0, 0, 7, 4, 0, 0 },
				{ 0, 4, 9, 2, 0, 6, 0, 0, 7 } };
		int[][] expectedValues = new int[][] { { 7, 8, 5, 4, 3, 9, 1, 2, 6 }, { 6, 1, 2, 8, 7, 5, 3, 4, 9 },
				{ 4, 9, 3, 6, 2, 1, 5, 7, 8 }, { 8, 5, 7, 9, 4, 3, 2, 6, 1 }, { 2, 6, 1, 7, 5, 8, 9, 3, 4 },
				{ 9, 3, 4, 1, 6, 2, 7, 8, 5 }, { 5, 7, 8, 3, 9, 4, 6, 1, 2 }, { 1, 2, 6, 5, 8, 7, 4, 9, 3 },
				{ 3, 4, 9, 2, 1, 6, 8, 5, 7 } };

		ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < inputValues.length; i++) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			for (int j = 0; j < inputValues[i].length; j++) {
				row.add(inputValues[i][j]);
			}
			input.add(row);
		}

		ArrayList<ArrayList<Integer>> expected = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < expectedValues.length; i++) {
			ArrayList<Integer> row = new ArrayList<Integer>();
			for (int j = 0; j < expectedValues[i].length; j++) {
				row.add(expectedValues[i][j]);
			}
			expected.add(row);
		}

		ArrayList<ArrayList<Integer>> actual = new SolveSudoku().solveSudoku(input);
		assertTrue(expected.equals(actual));

	}

}
