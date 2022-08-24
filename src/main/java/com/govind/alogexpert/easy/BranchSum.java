package com.govind.alogexpert.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BranchSum {
	public static class BinaryTree {
		int value;
		BinaryTree left;
		BinaryTree right;

		BinaryTree(int value) {
			this.value = value;
			this.left = null;
			this.right = null;
		}
	}

	public static List<Integer> branchSums(BinaryTree root) {
		List<Integer> sums = new ArrayList<Integer>();
		calculateBranchSums(root, 0, sums);
		return sums;
	}

	private static void calculateBranchSums(BinaryTree node, int runningSum, List<Integer> sums) {
		if (node == null)
			return;
		int newRunningSum = runningSum + node.value;
		if (node.left == null && node.right == null) {
			sums.add(newRunningSum);
			return;
		}
		calculateBranchSums(node.left, newRunningSum, sums);
		calculateBranchSums(node.right, newRunningSum, sums);

	}

	public static class TestBinaryTree extends BranchSum.BinaryTree {
		TestBinaryTree(int value) {
			super(value);
		}

		TestBinaryTree insert(List<Integer> values) {
			return insert(values, 0);
		}

		TestBinaryTree insert(List<Integer> values, int i) {
			if (i >= values.size())
				return null;

			List<TestBinaryTree> queue = new ArrayList<TestBinaryTree>();
			queue.add(this);
			while (queue.size() > 0) {
				TestBinaryTree current = queue.get(0);
				queue.remove(0);
				if (current.left == null) {
					current.left = new TestBinaryTree(values.get(i));
					break;
				}
				queue.add((TestBinaryTree) current.left);
				if (current.right == null) {
					current.right = new TestBinaryTree(values.get(i));
					break;
				}
				queue.add((TestBinaryTree) current.right);
			}
			insert(values, i + 1);
			return this;
		}

	}

	public static void main(String[] args) {
		TestBinaryTree tree = new TestBinaryTree(1);
		// new TestBinaryTree(1).insert(Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10));
		List<Integer> expected = new ArrayList<Integer>(Arrays.asList(15, 16, 18, 10, 11));
		branchSums(tree).equals(expected);

	}

}
