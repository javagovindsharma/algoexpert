package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BSTTravsal {
	public static List<Integer> inOrderTraverse(BST tree, List<Integer> array) {
		if (tree.left != null) {
			inOrderTraverse(tree.left, array);
		}
		array.add(tree.value);
		if (tree.right != null) {
			inOrderTraverse(tree.right, array);
		}
		return array;
	}

	public static List<Integer> preOrderTraverse(BST tree, List<Integer> array) {
		array.add(tree.value);
		if (tree.left != null) {
			preOrderTraverse(tree.left, array);
		}
		array.add(tree.value);
		if (tree.right != null) {
			preOrderTraverse(tree.right, array);
		}
		return array;
	}

	public static List<Integer> postOrderTraverse(BST tree, List<Integer> array) {
		if (tree.left != null) {
			postOrderTraverse(tree.left, array);
		}
		if (tree.right != null) {
			postOrderTraverse(tree.right, array);
		}
		array.add(tree.value);
		return array;
	}

	static class BST {
		public int value;
		public BST left;
		public BST right;

		public BST(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		BST root = new BST(10);
		root.left = new BST(5);
		root.left.left = new BST(2);
		root.left.left.left = new BST(1);
		root.left.right = new BST(5);
		root.right = new BST(15);
		root.right.right = new BST(22);

		List<Integer> inOrder = Arrays.asList(new Integer[] { 1, 2, 5, 5, 10, 15, 22 });
		List<Integer> preOrder = Arrays.asList(new Integer[] { 10, 5, 2, 1, 5, 15, 22 });
		List<Integer> postOrder = Arrays.asList(new Integer[] { 1, 2, 5, 5, 22, 15, 10 });
		inOrderTraverse(root, new ArrayList<Integer>()).equals(inOrder);
		preOrderTraverse(root, new ArrayList<Integer>()).equals(preOrder);
		postOrderTraverse(root, new ArrayList<Integer>()).equals(postOrder);

	}

}
