package com.govind.alogexpert.hard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxPathSumInBinaryTree {

	public static int maxPathSum(BinaryTree tree) {
		List<Integer> maxSumArray = findMaxSum(tree);
		return maxSumArray.get(1);
	}

	private static List<Integer> findMaxSum(BinaryTree tree) {
		if (tree == null) {
			return new ArrayList<Integer>(Arrays.asList(0, Integer.MIN_VALUE));
		}

		List<Integer> leftMaxSumArray = findMaxSum(tree.left);
		Integer leftMaxSumAsBranch = leftMaxSumArray.get(0);
		Integer leftMaxPathSum = leftMaxSumArray.get(1);

		List<Integer> rightMaxSumArray = findMaxSum(tree.right);
		Integer rightMaxSumAsBranch = rightMaxSumArray.get(0);
		Integer rightMaxPathSum = rightMaxSumArray.get(1);

		Integer maxChildSumasBranch = Math.max(leftMaxSumAsBranch, rightMaxSumAsBranch);
		Integer maxSumAsBranch = Math.max(maxChildSumasBranch + tree.value, tree.value);
		Integer maxSumAsRootNode = Math.max(leftMaxSumAsBranch + tree.value + rightMaxSumAsBranch, maxSumAsBranch);
		int maxPathSum = Math.max(leftMaxPathSum, Math.max(rightMaxPathSum, maxSumAsRootNode));

		return new ArrayList<Integer>(Arrays.asList(maxSumAsBranch, maxPathSum));
	}

	static class BinaryTree {
		public int value;
		public BinaryTree left;
		public BinaryTree right;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		TestBinaryTree test = new TestBinaryTree(1);
		test.insert(new int[] { 2, 3, 4, 5, 6, 7 }, 0);
		assertTrue(MaxPathSumInBinaryTree.maxPathSum(test) == 18);

	}

	static class TestBinaryTree extends MaxPathSumInBinaryTree.BinaryTree {
		public TestBinaryTree(int value) {
			super(value);
		}

		public void insert(int[] values, int i) {
			if (i >= values.length) {
				return;
			}
			ArrayDeque<MaxPathSumInBinaryTree.BinaryTree> queue = new ArrayDeque<MaxPathSumInBinaryTree.BinaryTree>();
			queue.addLast(this);
			while (queue.size() > 0) {
				MaxPathSumInBinaryTree.BinaryTree current = queue.pollFirst();
				if (current.left == null) {
					current.left = new MaxPathSumInBinaryTree.BinaryTree(values[i]);
					break;
				}
				queue.addLast(current.left);
				if (current.right == null) {
					current.right = new MaxPathSumInBinaryTree.BinaryTree(values[i]);
					break;
				}
				queue.addLast(current.right);
			}
			insert(values, i + 1);
		}
	}

}
