package com.govind.alogexpert.veryhard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllKindsOfNodeDepths {

	// solution 5
	public static int allKindsOfNodeDepths(BinaryTree root) {
		return allKindsOfNodeDepthsHelper1(root, 0);
	}
	private static int allKindsOfNodeDepthsHelper1(BinaryTree node, int depth) {
		if (node == null)
			return 0; 
        int depthSum=(depth*(depth+1))/2; 
		return depthSum + allKindsOfNodeDepthsHelper1(node.left, depth + 1)
				+ allKindsOfNodeDepthsHelper1(node.right, depth + 1);
	}
	// solution 5
	public static int allKindsOfNodeDepths5(BinaryTree root) {
		return allKindsOfNodeDepthsHelper(root, 0, 0);
	}

	private static int allKindsOfNodeDepthsHelper(BinaryTree node, int depthSum, int depth) {
		if (node == null)
			return 0;

		depthSum += depth;

		return depthSum + allKindsOfNodeDepthsHelper(node.left, depthSum, depth + 1)
				+ allKindsOfNodeDepthsHelper(node.right, depthSum, depth + 1);
	}

	// solution 4
	public static int allKindsOfNodeDepths4(BinaryTree root) {
		return getTreeInfo(root).sumOfAllDepths;
	}

	private static TreeInfo getTreeInfo(BinaryTree tree) {
		if (tree == null)
			return new TreeInfo(0, 0, 0);

		TreeInfo leftTreeInfo = getTreeInfo(tree.left);
		TreeInfo rightTreeInfo = getTreeInfo(tree.right);

		int sumofLeftDepths = leftTreeInfo.sumOfDepths + leftTreeInfo.numNodesInTree;
		int sumofRightDepths = rightTreeInfo.sumOfDepths + rightTreeInfo.numNodesInTree;

		int numNodesInTree = 1 + leftTreeInfo.numNodesInTree + rightTreeInfo.numNodesInTree;
		int sumOfDepths = sumofLeftDepths + sumofRightDepths;
		int sumOfAllDepths = sumOfDepths + leftTreeInfo.sumOfAllDepths + rightTreeInfo.sumOfAllDepths;

		return new TreeInfo(numNodesInTree, sumOfDepths, sumOfAllDepths);
	}

	static class TreeInfo {
		public int numNodesInTree;
		public int sumOfDepths;
		public int sumOfAllDepths;

		public TreeInfo(int numNodesInTree, int sumOfDepths, int sumOfAllDepths) {
			this.numNodesInTree = numNodesInTree;
			this.sumOfDepths = sumOfDepths;
			this.sumOfAllDepths = sumOfAllDepths;
		}
	}

	// solution 3
	public static int allKindsOfNodeDepths3(BinaryTree root) {
		Map<BinaryTree, Integer> nodeCounts = new HashMap<BinaryTree, Integer>();
		Map<BinaryTree, Integer> nodeDepths = new HashMap<BinaryTree, Integer>();
		addNodeCounts(root, nodeCounts);
		addNodeDepths(root, nodeDepths, nodeCounts);
		return sumAllNodeDepths(root, nodeDepths);
	}

	private static int sumAllNodeDepths(BinaryTree node, Map<BinaryTree, Integer> nodeDepths) {
		if (node == null)
			return 0;
		return sumAllNodeDepths(node.left, nodeDepths) + sumAllNodeDepths(node.right, nodeDepths)
				+ nodeDepths.get(node);
	}

	private static void addNodeDepths(BinaryTree node, Map<BinaryTree, Integer> nodeDepths,
			Map<BinaryTree, Integer> nodeCounts) {
		nodeCounts.put(node, 0);
		if (node.left != null) {
			addNodeDepths(node.left, nodeDepths, nodeCounts);
			nodeDepths.put(node, nodeDepths.get(node) + nodeDepths.get(node.left) + nodeCounts.get(node.left));
		}
		if (node.right != null) {
			addNodeDepths(node.right, nodeDepths, nodeCounts);
			nodeDepths.put(node, nodeDepths.get(node) + nodeDepths.get(node.right) + nodeCounts.get(node.right));
		}

	}

	private static void addNodeCounts(BinaryTree node, Map<BinaryTree, Integer> nodeCounts) {
		nodeCounts.put(node, 1);
		if (node.left != null) {
			addNodeCounts(node.left, nodeCounts);
			nodeCounts.put(node, nodeCounts.get(node) + nodeCounts.get(node.left));
		}
		if (node.right != null) {
			addNodeCounts(node.right, nodeCounts);
			nodeCounts.put(node, nodeCounts.get(node) + nodeCounts.get(node.right));
		}

	}

	// solution 2
	public static int allKindsOfNodeDepths2(BinaryTree root) {
		if (root == null)
			return 0;
		return allKindsOfNodeDepths2(root.left) + allKindsOfNodeDepths2(root.right) + nodeDepths(root, 0);
	}

	// solution 1
	public static int allKindsOfNodeDepths1(BinaryTree root) {
		int sumOfAllDepths = 0;
		List<BinaryTree> stack = new ArrayList<BinaryTree>();
		stack.add(root);

		while (stack.size() > 0) {
			BinaryTree node = stack.remove(stack.size() - 1);
			if (node == null)
				continue;
			sumOfAllDepths += nodeDepths(node, 0);
			stack.add(node.left);
			stack.add(node.right);
		}
		return sumOfAllDepths;
	}

	private static int nodeDepths(BinaryTree node, int depth) {
		if (node == null)
			return 0;
		return depth + nodeDepths(node.left, depth + 1) + nodeDepths(node.right, depth + 1);
	}

	static class BinaryTree {
		int value;
		BinaryTree left;
		BinaryTree right;

		public BinaryTree(int value) {
			this.value = value;
			left = null;
			right = null;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
