package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;

public class NonAttackingQueens {

	// solution 1
	public int nonAttackingQueens1(int n) {
		int[] columnPlaements = new int[n];
		return getNumberOfNonAttackingQueenPlacments(0, columnPlaements, n);
	}

	private int getNumberOfNonAttackingQueenPlacments(int row, int[] columnPlaements, int boardSize) {
		if (row == boardSize)
			return 1;
		int validPlacements = 0;

		for (int col = 0; col < boardSize; col++) {
			if (isNonAttackingPlacement(row, col, columnPlaements)) {
				columnPlaements[row] = col;
				validPlacements += getNumberOfNonAttackingQueenPlacments(row + 1, columnPlaements, boardSize);
			}
		}
		return validPlacements;
	}

	private boolean isNonAttackingPlacement(int row, int col, int[] columnPlaements) {
		for (int previousRow = 0; previousRow < row; previousRow++) {
			int columnToCheck = columnPlaements[previousRow];
			boolean sameColumn = (columnToCheck == col);
			boolean onDiagonal = Math.abs(columnToCheck - col) == (row - previousRow);

			if (sameColumn || onDiagonal) {
				return false;
			}
		}

		return true;
	}

	// solution 2
	public int nonAttackingQueens(int n) {
		HashSet<Integer> blockedColumn = new HashSet<Integer>();
		HashSet<Integer> blockedUpDiagonal = new HashSet<Integer>();
		HashSet<Integer> blockedDownDiagonal = new HashSet<Integer>();
		return getNumberOfNonAttackingQueenPlacments(0, blockedColumn, blockedUpDiagonal, blockedDownDiagonal, n);
	}

	private int getNumberOfNonAttackingQueenPlacments(int row, HashSet<Integer> blockedColumn,
			HashSet<Integer> blockedUpDiagonal, HashSet<Integer> blockedDownDiagonal, int boardSize) {

		if (row == boardSize)
			return 1;
		int validPlacements = 0;
		for (int col = 0; col < boardSize; col++) {
			if (isNonAttackingPlacement(row, col, blockedColumn, blockedUpDiagonal, blockedDownDiagonal)) {
				placeQueen(row, col, blockedColumn, blockedUpDiagonal, blockedDownDiagonal);
				validPlacements += getNumberOfNonAttackingQueenPlacments(row + 1, blockedColumn, blockedUpDiagonal,
						blockedDownDiagonal, boardSize);
				removeQueen(row, col, blockedColumn, blockedUpDiagonal, blockedDownDiagonal);
			}
		}
		return validPlacements;
	}

	private boolean isNonAttackingPlacement(int row, int col, HashSet<Integer> blockedColumn,
			HashSet<Integer> blockedUpDiagonal, HashSet<Integer> blockedDownDiagonal) {
		if (blockedColumn.contains(col)) {
			return false;
		} else if (blockedUpDiagonal.contains(row + col)) {
			return false;
		} else if (blockedDownDiagonal.contains(row - col)) {
			return false;
		}
		return true;
	}

	private void placeQueen(int row, int col, HashSet<Integer> blockedColumn, HashSet<Integer> blockedUpDiagonal,
			HashSet<Integer> blockedDownDiagonal) {
		blockedColumn.add(col);
		blockedUpDiagonal.add(row + col);
		blockedDownDiagonal.add(row - col);

	}

	private void removeQueen(int row, int col, HashSet<Integer> blockedColumn, HashSet<Integer> blockedUpDiagonal,
			HashSet<Integer> blockedDownDiagonal) {
		blockedColumn.remove(col);
		blockedUpDiagonal.remove(row + col);
		blockedDownDiagonal.remove(row - col);

	}

	public static void main(String[] args) {
		int input = 4;
		int expected = 2;
		int actual = new NonAttackingQueens().nonAttackingQueens(input);
		assertTrue(expected == actual);

	}

}
