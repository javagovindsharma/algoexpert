package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.Stack;

public class CompareLeafTraversal {

	static class BinaryTree {
		public int value;
		public BinaryTree left = null;
		public BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}
	// solution 1

	public boolean compareLeafTraversal1(BinaryTree tree1, BinaryTree tree2) {
		Stack<BinaryTree> tree1TraversalStack = new Stack<>();
		tree1TraversalStack.push(tree1);
		Stack<BinaryTree> tree2TraversalStack = new Stack<>();
		tree2TraversalStack.push(tree2);

		while (tree1TraversalStack.size() > 0 && tree2TraversalStack.size() > 0) {
			BinaryTree tree1Leaf = getNextleafNode(tree1TraversalStack);
			BinaryTree tree2Leaf = getNextleafNode(tree2TraversalStack);

			if (tree1Leaf.value != tree2Leaf.value) {
				return false;
			}
		}
		return (tree1TraversalStack.size() == 0) && (tree2TraversalStack.size() == 0);
	}

	private BinaryTree getNextleafNode(Stack<BinaryTree> traversalStack) {
		BinaryTree currentNode = traversalStack.pop();

		while (!isLeafNode(currentNode)) {
			if (currentNode.right != null) {
				traversalStack.push(currentNode.right);
			}

			if (currentNode.left != null) {
				traversalStack.push(currentNode.left);
			}

			currentNode = traversalStack.pop();
		}
		return currentNode;
	}

	private boolean isLeafNode(BinaryTree node) {
		return (node.left == null) && (node.right == null);
	}

	// solution 2
	public boolean compareLeafTraversal(BinaryTree tree1, BinaryTree tree2) {
		BinaryTree tree1LeafNodeLinkedList = connectLeafNode(tree1, null, null)[0];
		BinaryTree tree2LeafNodeLinkedList = connectLeafNode(tree2, null, null)[0];

		BinaryTree list1Currentnode = tree1LeafNodeLinkedList;
		BinaryTree list2Currentnode = tree2LeafNodeLinkedList;

		while(list1Currentnode!=null && list2Currentnode!=null) {
			if(list1Currentnode.value!=list2Currentnode.value) return false;
		
			list1Currentnode=list1Currentnode.right;
			list2Currentnode=list2Currentnode.right;
		}
		return list1Currentnode==null && list2Currentnode==null;
	}

	private BinaryTree[] connectLeafNode(BinaryTree currentNode, BinaryTree head, BinaryTree previousNode) {
		if(currentNode==null) return new BinaryTree[] {head,previousNode};
	
		if(isLeafNode(currentNode)) {
			if(previousNode==null) {
				head=currentNode;
			}else {
				previousNode.right=currentNode;
			}
			previousNode=currentNode;
		}
		BinaryTree[] nodes=connectLeafNode(currentNode.left,head,previousNode);
		BinaryTree leftHead=nodes[0];
		BinaryTree leftPreviousNode=nodes[1];
		return connectLeafNode(currentNode.right,leftHead,leftPreviousNode);
	}

	public static void main(String[] args) {
		CompareLeafTraversal.BinaryTree tree1 = new CompareLeafTraversal.BinaryTree(1);
		tree1.left = new CompareLeafTraversal.BinaryTree(2);
		tree1.right = new CompareLeafTraversal.BinaryTree(3);
		tree1.left.left = new CompareLeafTraversal.BinaryTree(4);
		tree1.left.right = new CompareLeafTraversal.BinaryTree(5);
		tree1.right.right = new CompareLeafTraversal.BinaryTree(6);
		tree1.left.right.left = new CompareLeafTraversal.BinaryTree(7);
		tree1.left.right.right = new CompareLeafTraversal.BinaryTree(8);

		CompareLeafTraversal.BinaryTree tree2 = new CompareLeafTraversal.BinaryTree(1);
		tree2.left = new CompareLeafTraversal.BinaryTree(2);
		tree2.right = new CompareLeafTraversal.BinaryTree(3);
		tree2.left.left = new CompareLeafTraversal.BinaryTree(4);
		tree2.left.right = new CompareLeafTraversal.BinaryTree(7);
		tree2.right.right = new CompareLeafTraversal.BinaryTree(5);
		tree2.right.right.left = new CompareLeafTraversal.BinaryTree(8);
		tree2.right.right.right = new CompareLeafTraversal.BinaryTree(6);

		boolean expected = true;
		boolean actual = new CompareLeafTraversal().compareLeafTraversal(tree1, tree2);
		assertTrue(expected == actual);

	}

}
