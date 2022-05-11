package com.govind.alogexpert.medum;

import java.util.ArrayDeque;

public class BinaryTreeDiameter {

	static class BinaryTree {
		public int value;
		public BinaryTree left = null;
		public BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	
	static class TreeInfo{
		public int diameter;
		public int height;
		
		public TreeInfo(int diameter,int height) {
			this.diameter=diameter;
			this.height=height;
		}
	}
	public int binaryTreeDiameter(BinaryTree tree) {
		return getTreeInfo(tree).diameter;
	}
	
	public TreeInfo getTreeInfo(BinaryTree tree) {
		if(tree==null) {
		return new TreeInfo(0,0);
		}
		
		TreeInfo leftTreeInfo=getTreeInfo(tree.left);
		TreeInfo rightTreeInfo=getTreeInfo(tree.right);
		
		int longestPathThroughRoot=leftTreeInfo.height+rightTreeInfo.height;
		int maxDiameterSoFar=Math.max(leftTreeInfo.diameter, rightTreeInfo.diameter);
		int currentDiameter=Math.max(longestPathThroughRoot, maxDiameterSoFar);
		int currentHeight=1+Math.max(leftTreeInfo.height, rightTreeInfo.height);
		return new TreeInfo(currentDiameter,currentHeight);
	}
	

// for testing
	public static void main(String[] args) {
		TestBinaryTree input = new TestBinaryTree(1);
		input.insert(new int[] { 2, 3, 4, 5, 6, 7 }, 0);
		int expected = 4;
		int actual = new BinaryTreeDiameter().binaryTreeDiameter(input);
		System.out.println(expected == actual);

	}

}
class TestBinaryTree extends BinaryTreeDiameter.BinaryTree {
	public TestBinaryTree(int value) {
		super(value);
	}

	public void insert(int[] values, int i) {
		if (i >= values.length) {
			return;
		}
		ArrayDeque<BinaryTreeDiameter.BinaryTree> queue = new ArrayDeque<BinaryTreeDiameter.BinaryTree>();
		queue.addLast(this);
		while (queue.size() > 0) {
			BinaryTreeDiameter.BinaryTree current = queue.pollFirst();
			if (current.left == null) {
				current.left = new BinaryTreeDiameter.BinaryTree(values[i]);
				break;
			}
			queue.addLast(current.left);
			if (current.right == null) {
				current.right = new BinaryTreeDiameter.BinaryTree(values[i]);
				break;
			}
			queue.addLast(current.right);
		}
		insert(values, i + 1);
	}
}
