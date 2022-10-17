package com.govind.alogexpert.medum;

public class BalanacedHeightTree {

	static class BinaryTree {
		public int value;
		public BinaryTree left = null;
		public BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}
	
	static class TreeInfo{
		public boolean isBalanced;
		public int height;
		
		public TreeInfo(boolean isBalanced,int height) {
			this.isBalanced=isBalanced;
			this.height=height;
		}
	}

	public boolean heightBalancedBinaryTree(BinaryTree tree) {
		TreeInfo treeInfo=getTreeInfo(tree);
		return treeInfo.isBalanced;
	}
	
	public TreeInfo getTreeInfo(BinaryTree node) {
		if(node==null) {
			return new TreeInfo(true,-1);
		}
		TreeInfo leftSubtreeInfo=getTreeInfo(node.left);
		TreeInfo rightSubtreeInfo=getTreeInfo(node.right);
		boolean isBalanced=leftSubtreeInfo.isBalanced&& rightSubtreeInfo.isBalanced&& Math.abs(leftSubtreeInfo.height-rightSubtreeInfo.height)<=1;
		int height=Math.max(leftSubtreeInfo.height, rightSubtreeInfo.height)+1;
		return new TreeInfo(isBalanced,height);
	}

	public static void main(String[] args) {
		BalanacedHeightTree.BinaryTree root = new BalanacedHeightTree.BinaryTree(1);
		    root = new BalanacedHeightTree.BinaryTree(1);
		    root.left = new BalanacedHeightTree.BinaryTree(2);
		    root.right = new BalanacedHeightTree.BinaryTree(3);
		    root.left.left = new BalanacedHeightTree.BinaryTree(4);
		    root.left.right = new BalanacedHeightTree.BinaryTree(5);
		    root.right.right = new BalanacedHeightTree.BinaryTree(6);
		    root.left.right.left = new BalanacedHeightTree.BinaryTree(7);
		    root.left.right.right = new BalanacedHeightTree.BinaryTree(8);
		    @SuppressWarnings("unused")
			boolean expected = true;

	}

}
