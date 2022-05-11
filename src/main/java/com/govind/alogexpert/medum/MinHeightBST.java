package com.govind.alogexpert.medum;

import java.util.List;

public class MinHeightBST {
	public static BST minHeightBst(List<Integer> array) {
		return constructMinHeight(array, null,0, array.size() - 1);
	}
	public static BST constructMinHeight(List<Integer> array,BST bst, int startIdx, int endIdx) {
		if (endIdx < startIdx)
			return null;
		int midIdx = (startIdx + endIdx) / 2;
		int valueToAdd=array.get(midIdx);
		if(bst==null) {
			bst=new BST(valueToAdd);
		}else {
			bst.insert(valueToAdd);
		}
		
		constructMinHeight(array,bst,startIdx, midIdx -1);
		constructMinHeight(array, bst, midIdx+1, endIdx);
		return bst;

	}
	//solution2
	public static BST minHeightBstSol2(List<Integer> array) {
		return constructMinHeight2(array, null,0, array.size() - 1);
	}
	public static BST constructMinHeight2(List<Integer> array,BST bst, int startIdx, int endIdx) {
		if (endIdx < startIdx)
			return null;
		int midIdx = (startIdx + endIdx) / 2;
		BST newBstNode = new BST(array.get(midIdx));
		if(bst==null) {
			bst=newBstNode;
		}else {
			if(array.get(midIdx)<bst.value) {
				bst.left = newBstNode;
				bst=bst.left;
			}else {
				bst.right = newBstNode;
				bst=bst.right;
			}
		}
		
		constructMinHeight(array,bst,startIdx, midIdx -1);
		constructMinHeight(array, bst, midIdx+1, endIdx);
		return bst;

	}
	// solution 3
	public static BST minHeightBstSol3(List<Integer> array) {
		return constructMinHeight(array, 0, array.size() - 1);
	}

	public static BST constructMinHeight(List<Integer> array, int startIdx, int endIdx) {
		if (endIdx < startIdx)
			return null;
		int midIdx = (startIdx + endIdx) / 2;
		BST bst = new BST(array.get(midIdx));
		bst.left = constructMinHeight(array, startIdx, midIdx - 1);
		bst.right = constructMinHeight(array, midIdx + 1, endIdx);
		return bst;

	}

	static class BST {
		public int value;
		public BST left;
		public BST right;

		public BST(int value) {
			this.value = value;
			left = null;
			right = null;
		}

		public void insert(int value) {
			if (value < this.value) {
				if (left == null) {
					left = new BST(value);
				} else {
					left.insert(value);
				}
			} else {
				if (right == null) {
					right = new BST(value);
				} else {
					right.insert(value);
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
