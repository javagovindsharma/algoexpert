package com.govind.alogexpert.medum;

public class ValidateBST {
	public static boolean validateBst(BST tree) {
		return validateBst(tree,Integer.MIN_VALUE,Integer.MAX_VALUE); 
	}

	public static boolean validateBst(BST tree,int minValue,int maxValue) {

		 if(tree.value<minValue || tree.value>=maxValue) {
			 return false;
		 }
		 if(tree.left!=null && !validateBst(tree.left,minValue,tree.value)) {
			 return false;
		 }
		 if(tree.right!=null && !validateBst(tree.right,tree.value,maxValue)) {
			 return false;
		 }
		 
		return true;
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
		root.right.left = new BST(13);
		root.right.left.right = new BST(14);
		root.right.right = new BST(22);
		System.out.println(validateBst(root));
	}
}
