package com.govind.alogexpert.medum;

import java.util.ArrayList;

public class KthLargetValueinBST {
	static class BST {
		public int value;
		public BST left = null;
		public BST right = null;

		public BST(int value) {
			this.value = value;
		}
	}

	static class TreeInfo {

		public int numberOfNodesVisited;
		public int latestNodesVisited;

		public TreeInfo(int numberOfNodesVisited, int latestVisitedNodeValue) {
			this.numberOfNodesVisited = numberOfNodesVisited;
			this.latestNodesVisited = latestVisitedNodeValue;
		}
	}

	public int findKthLargestValueInBst(BST tree, int k) {
		TreeInfo treeInfo = new TreeInfo(0, -1);
		// reverseOrderTraverse(tree, k, treeInfo);
		return treeInfo.latestNodesVisited;
	}

	public void reverseOrderTraverse(BST node,int k,TreeInfo treeInfo) {
		if (node == null || treeInfo.numberOfNodesVisited>=k)return;
			
		reverseOrderTraverse(node.right,k,treeInfo);
		if(treeInfo.numberOfNodesVisited<k) {
			treeInfo.numberOfNodesVisited+=1;
			treeInfo.numberOfNodesVisited=node.value;
			reverseOrderTraverse(node.left,k,treeInfo);
		}
	}

	// solution 2
	public int findKthLargestValueInBstSol2(BST tree, int k) {
		ArrayList<Integer> sortedNodeValues = new ArrayList<Integer>();
		inOrderTraverseSol2(tree, sortedNodeValues);
		return sortedNodeValues.get(sortedNodeValues.size() - k);
	}

	public void inOrderTraverseSol2(BST node, ArrayList<Integer> sortedNodeValues) {
		if (node == null)
			return;

		inOrderTraverseSol2(node.left, sortedNodeValues);
		sortedNodeValues.add(node.value);
		inOrderTraverseSol2(node.right, sortedNodeValues);
	}

	public static void main(String[] args) {
		BST root = new BST(15);
		root.left = new BST(5);
		root.left.left = new BST(2);
		root.left.left.left = new BST(1);
		root.left.left.right = new BST(3);
		root.left.right = new BST(5);
		root.right = new BST(20);
		root.right.left = new BST(17);
		root.right.right = new BST(22);
		int k = 3;
		int expected = 17;
		int actual = new KthLargetValueinBST().findKthLargestValueInBst(root, k);
		System.out.println(expected == actual);

	}

}
