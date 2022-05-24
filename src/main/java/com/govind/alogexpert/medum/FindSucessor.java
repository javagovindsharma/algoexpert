package com.govind.alogexpert.medum;

import java.util.ArrayList;

public class FindSucessor {
	static class BinaryTree {
		public int value;
		public BinaryTree left = null;
		public BinaryTree right = null;
		public BinaryTree parent = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

//solution2
	public BinaryTree findSuccessor(BinaryTree tree, BinaryTree node) {
		if (node.right != null)
			return getLeftmostChild(node.right);
		return getRightmostParent(node);
	}

	public BinaryTree getLeftmostChild(BinaryTree node) {
		BinaryTree currentNode=node;
		while(currentNode.left!=null) {
			currentNode=currentNode.left;
		}
		return currentNode;
	}

	public BinaryTree getRightmostParent(BinaryTree node) {
		BinaryTree currentNode=node;
		while(currentNode.parent!=null && currentNode.parent.right==currentNode) {
			currentNode=currentNode.parent;
		}
		return currentNode.parent;
	}

	// solution 1
	public BinaryTree findSuccessor1(BinaryTree tree, BinaryTree node) {
		ArrayList<BinaryTree> inOrderTraversalOrder = new ArrayList<BinaryTree>();
		getInOrderTraversalOrder(tree, inOrderTraversalOrder);

		for (int i = 0; i < inOrderTraversalOrder.size(); i++) {
			BinaryTree currentNode = inOrderTraversalOrder.get(i);
			if (currentNode != node) {
				continue;
			}
			if (i == inOrderTraversalOrder.size() - 1) {
				return null;
			}
			return inOrderTraversalOrder.get(i + 1);
		}
		return null;
	}

	void getInOrderTraversalOrder(BinaryTree node, ArrayList<BinaryTree> order) {
		if (node == null)
			return;

		getInOrderTraversalOrder(node.left, order);
		order.add(node);
		getInOrderTraversalOrder(node.right, order);

	}

	public static void main(String[] args) {
		FindSucessor.BinaryTree root = new FindSucessor.BinaryTree(1);
		root.left = new FindSucessor.BinaryTree(2);
		root.left.parent = root;
		root.right = new FindSucessor.BinaryTree(3);
		root.right.parent = root;
		root.left.left = new FindSucessor.BinaryTree(4);
		root.left.left.parent = root.left;
		root.left.right = new FindSucessor.BinaryTree(5);
		root.left.right.parent = root.left;
		root.left.left.left = new FindSucessor.BinaryTree(6);
		root.left.left.left.parent = root.left.left;
		FindSucessor.BinaryTree node = root.left.right;
		FindSucessor.BinaryTree expected = root;
		FindSucessor.BinaryTree output = new FindSucessor().findSuccessor(root, node);
	}

}
