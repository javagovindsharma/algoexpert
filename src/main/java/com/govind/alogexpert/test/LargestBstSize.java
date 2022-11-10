package com.govind.alogexpert.test;

public class LargestBstSize {

	public static int largestBstSize(BinaryTree tree) {
		return getTreeInfo(tree).runningLargestbstSize;
	}

	public static TreeInfo getTreeInfo(BinaryTree tree) {
		if (tree == null)
			return new TreeInfo(true, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 0);

		TreeInfo leftTreeinfo = getTreeInfo(tree.left);
		TreeInfo rightTreeinfo = getTreeInfo(tree.right);
		int treeSize=1+leftTreeinfo.treeSize+rightTreeinfo.treeSize;
		boolean satisfiesBstprop=tree.value>leftTreeinfo.maxValue && tree.value <=rightTreeinfo.minvalue;
		boolean isBst=satisfiesBstprop && leftTreeinfo.isBst && rightTreeinfo.isBst;
		
		int maxValue=Math.max(tree.value, Math.max(leftTreeinfo.maxValue, rightTreeinfo.maxValue));
		int minvalue=Math.max(tree.value, Math.max(leftTreeinfo.minvalue, rightTreeinfo.minvalue));
		
		int runningLargestBstSize=0;
		if(isBst) {
			runningLargestBstSize=treeSize;
		}else {
			runningLargestBstSize=Math.max(leftTreeinfo.runningLargestbstSize, rightTreeinfo.runningLargestbstSize);
		}
		return new TreeInfo(isBst,maxValue,minvalue,runningLargestBstSize,treeSize);
	}

	static class TreeInfo{
		boolean isBst;
		int maxValue;
		int minvalue;
		int runningLargestbstSize;
		int treeSize;
		
		public TreeInfo(boolean isBst, int maxValue, int minvalue, int runningLargestbstSize, int treeSize) {
			this.isBst = isBst;
			this.maxValue = maxValue;
			this.minvalue = minvalue;
			this.runningLargestbstSize = runningLargestbstSize;
			this.treeSize = treeSize;
		}	
	}

// This is an input class. Do not edit.
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
