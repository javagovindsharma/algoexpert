package com.govind.alogexpert.test;

import static org.junit.Assert.assertTrue;

public class SubtreesWithinRange {
	// solution 1
	public static int subtreesWithinRange1(BST tree, int[] targetRange) {
		return getTreeInfo(tree, targetRange).numSubtreeWithinRange;
	}

	private static TreeInfo getTreeInfo(BST tree, int[] targetRange) {
		int numSubtreeWithinRange = 0;
		int maxValue = 0;
		int minValue = 0;

		if (tree.left != null) {
			TreeInfo leftSubtreeInfo = getTreeInfo(tree.left, targetRange);
			minValue = leftSubtreeInfo.minValue;
			numSubtreeWithinRange += leftSubtreeInfo.numSubtreeWithinRange;
		}
		if (tree.right != null) {
			TreeInfo rightSubtreeInfo = getTreeInfo(tree.left, targetRange);
			maxValue = rightSubtreeInfo.maxValue;
			numSubtreeWithinRange += rightSubtreeInfo.numSubtreeWithinRange;
		}

		if (minValue >= targetRange[0] && maxValue <= targetRange[1])
			numSubtreeWithinRange++;
		return new TreeInfo(maxValue, minValue, numSubtreeWithinRange);
	}

	static class TreeInfo {
		public int maxValue;
		public int minValue;
		public int numSubtreeWithinRange;

		public TreeInfo(int maxValue, int minValue, int numSubtreeWithinRange) {
			this.maxValue = maxValue;
			this.minValue = minValue;
			this.numSubtreeWithinRange = numSubtreeWithinRange;
		}

	}

	// solution 2
	public static int subtreesWithinRange(BST tree, int[] targetRange) {
		Result answer = new Result(0);
		isTreeWithinRange(tree, targetRange, answer);
		return getTreeInfo(tree, targetRange).numSubtreeWithinRange;
	}

	private static boolean isTreeWithinRange(BST tree, int[] targetRange, Result answer) {
		if (tree == null)
			return true;
		boolean leftTreeWithinRange = isTreeWithinRange(tree.left, targetRange, answer);
		boolean rightTreeWithinRange = isTreeWithinRange(tree.right, targetRange, answer);
		boolean nodeInRange = tree.value >= targetRange[0] && tree.value <= targetRange[1];
		boolean treeWithinRange = leftTreeWithinRange && rightTreeWithinRange && nodeInRange;
		if (treeWithinRange)
			answer.value++;
		return treeWithinRange;
	}

	static class Result {

		public int value;

		public Result(int value) {
			this.value = value;
		}

	}

	// This is an input class. Do not edit.
	static class BST {
		public int value;
		public BST left;
		public BST right;

		public BST(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}

	public static void main(String[] args) {
		SubtreesWithinRange.BST root = new SubtreesWithinRange.BST(10);
		root.left = new SubtreesWithinRange.BST(5);
		root.left.left = new SubtreesWithinRange.BST(2);
		root.left.left.left = new SubtreesWithinRange.BST(1);
		root.left.right = new SubtreesWithinRange.BST(8);
		root.left.right.left = new SubtreesWithinRange.BST(5);
		root.left.right.right = new SubtreesWithinRange.BST(9);
		root.right = new SubtreesWithinRange.BST(15);
		root.right.left = new SubtreesWithinRange.BST(13);
		root.right.left.right = new SubtreesWithinRange.BST(14);
		root.right.right = new SubtreesWithinRange.BST(22);
		int[] targetRange = new int[] { 5, 15 };
		assertTrue(SubtreesWithinRange.subtreesWithinRange(root, targetRange) == 5);

	}

}
