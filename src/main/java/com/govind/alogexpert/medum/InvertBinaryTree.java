package com.govind.alogexpert.medum;

import java.util.ArrayDeque;

public class InvertBinaryTree {

	//Solution 1
	public static void invertBinaryTree1(BinaryTree tree) {
		ArrayDeque<BinaryTree> queue=new ArrayDeque<BinaryTree>();
		queue.addLast(tree);
		while(queue.size()>0) {
			BinaryTree current=queue.pollFirst();
			swapLeftAndRight(current);
			if(current.left!=null) {
				queue.add(current.left);
			}
			if(current.right!=null) {
				queue.add(current.right);
			}
		}
	}
	
	private static void swapLeftAndRight(BinaryTree tree) {
		BinaryTree left=tree.left;
		tree.left=tree.right;
		tree.right=left;
	}
	
	
	// Solution 2
	public static void invertBinaryTree(BinaryTree tree) {
		if(tree==null) {
			return;
		}
		swapLeftAndRight(tree);
		invertBinaryTree(tree.left);
		invertBinaryTree(tree.right);
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
		// TODO Auto-generated method stub

	}

}
