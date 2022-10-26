package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class FlattenBinaryTree {

	// solution 2
	public static BinaryTree flattenBinaryTree(BinaryTree root) {
		BinaryTree leftMost = flattenTree(root)[0];

		return leftMost;
	}

	private static BinaryTree[] flattenTree(BinaryTree node) {
		BinaryTree leftMost;
		BinaryTree rightMost;

		if (node.left == null) {
			leftMost = node;
		} else {
			BinaryTree[] leftAndRightMostNodes = flattenTree(node.left);
			connectNodes(leftAndRightMostNodes[1], node);
			leftMost = leftAndRightMostNodes[0];
		}

		if (node.right == null) {
			rightMost = node;
		} else {
			BinaryTree[] leftAndRightMostNodes = flattenTree(node.right);
			connectNodes(node, leftAndRightMostNodes[0]);
			rightMost = leftAndRightMostNodes[1];
		}
		return new BinaryTree[] { leftMost, rightMost };
	}

	private static void connectNodes(BinaryTree left, BinaryTree right) {
		left.right = right;
		right.left = left;

	}

	// solution 1
	public static BinaryTree flattenBinaryTree1(BinaryTree root) {
		List<BinaryTree> inOrderNodes = getNodesInOrder(root, new ArrayList<BinaryTree>());
		for (int i = 0; i < inOrderNodes.size() - 1; i++) {
			BinaryTree leftNode = inOrderNodes.get(i);
			BinaryTree rightNode = inOrderNodes.get(i + 1);
			leftNode.right = rightNode;
			rightNode.left = leftNode;
		}
		return inOrderNodes.get(0);
	}

	private static List<BinaryTree> getNodesInOrder(BinaryTree tree, ArrayList<BinaryTree> array) {
		if (tree != null) {
			getNodesInOrder(tree.left, array);
			array.add(tree);
			getNodesInOrder(tree.right, array);
		}
		return array;
	}

	// This is the class of the input root. Do not edit it.
	static class BinaryTree {
		int value;
		BinaryTree left = null;
		BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		FlattenBinaryTree.BinaryTree root = new FlattenBinaryTree.BinaryTree(1);
		insert(root, new int[] { 2, 3, 4, 5, 6 });
		root.left.right.left = new FlattenBinaryTree.BinaryTree(7);
		root.left.right.right = new FlattenBinaryTree.BinaryTree(8);
		FlattenBinaryTree.BinaryTree leftMostNode = FlattenBinaryTree.flattenBinaryTree(root);
		List<Integer> leftToRightToLeft = leftToRightToLeft(leftMostNode);
		List<Integer> expected = new ArrayList<Integer>(Arrays.asList(4, 2, 7, 5, 8, 1, 6, 3, 3, 6, 1, 8, 5, 7, 2, 4));
		assertTrue(expected.equals(leftToRightToLeft));
	}

	public static void insert(FlattenBinaryTree.BinaryTree root, int[] values) {
		insert(root, values, 0);
	}

	public static void insert(FlattenBinaryTree.BinaryTree root, int[] values, int i) {
		if (i >= values.length) {
			return;
		}
		Deque<FlattenBinaryTree.BinaryTree> queue = new ArrayDeque<FlattenBinaryTree.BinaryTree>();
		queue.addLast(root);
		while (queue.size() > 0) {
			FlattenBinaryTree.BinaryTree current = queue.pollFirst();
			if (current.left == null) {
				current.left = new FlattenBinaryTree.BinaryTree(values[i]);
				break;
			}
			queue.addLast(current.left);
			if (current.right == null) {
				current.right = new FlattenBinaryTree.BinaryTree(values[i]);
				break;
			}
			queue.addLast(current.right);
		}
		insert(root, values, i + 1);
	}

	public static List<Integer> leftToRightToLeft(FlattenBinaryTree.BinaryTree leftMost) {
		List<Integer> nodes = new ArrayList<Integer>();
		FlattenBinaryTree.BinaryTree current = leftMost;
		while (current.right != null) {
			nodes.add(current.value);
			current = current.right;
		}
		nodes.add(current.value);
		while (current != null) {
			nodes.add(current.value);
			current = current.left;
		}
		return nodes;
	}

}
