package com.govind.alogexpert.hard;

import static org.junit.Assert.assertTrue;

public class ValidationThreeNodes {

	static class BST {
		public int value;
		public BST left = null;
		public BST right = null;

		public BST(int value) {
			this.value = value;
		}
	}

	public boolean validateThreeNodes1(BST nodeOne, BST nodeTwo, BST nodeThree) {
		if (isDescendant(nodeTwo, nodeOne)) {
			return isDescendant(nodeThree, nodeTwo);
		}
		if (isDescendant(nodeTwo, nodeThree)) {
			return isDescendant(nodeOne, nodeTwo);
		}
		return false;
	}

// solution 2
	private boolean isDescendant(BST node, BST target) {
		while (node != null && node != target) {
			 node= (target.value < node.value) ?
					node.left:node.right;
		}
		return node == target;
	}
	
	//solution 3
	
	public boolean validateThreeNodes(BST nodeOne, BST nodeTwo, BST nodeThree) {
		BST searchOne = nodeOne;
		BST searchTwo = nodeThree;
		while(true) {
			boolean foundThreeFromOne = searchOne ==nodeThree;
			boolean foundOneFromThree = searchTwo ==nodeOne;
			boolean foundNodeTwo = (searchOne ==nodeTwo) || (searchTwo==nodeTwo);
			boolean finishedSearching = (searchOne==null)&&(searchTwo==null);
			
			if(foundThreeFromOne || foundOneFromThree  || foundNodeTwo || finishedSearching) {
				break;
			}
			if(searchOne!=null) {
				searchOne=(searchOne.value>nodeTwo.value)?searchOne.left:searchOne.right;
			}
			if(searchTwo!=null) {
				searchTwo=(searchTwo.value>nodeTwo.value)?searchTwo.left:searchTwo.right;
			}
		}
		boolean foundNodeFromOther=(searchOne==nodeThree)||(searchTwo==nodeOne);
		boolean foundNodeTwo = (searchOne == nodeTwo) || (searchTwo == nodeTwo);
		
		if(!foundNodeTwo || foundNodeFromOther) {
			return false;
		}
		return searchForTarget(nodeTwo,(searchOne==nodeTwo)?nodeThree:nodeOne);
	}
	
	private boolean searchForTarget(BST node, BST target) {
		while(node!=null && node !=target) {
			node= (target.value < node.value)? node.left: node.right;
		}
		return node==target;
	}

	public static void main(String[] args) {
		BST root = new ValidationThreeNodes.BST(5);
		root.left = new ValidationThreeNodes.BST(2);
		root.right = new ValidationThreeNodes.BST(7);
		root.left.left = new ValidationThreeNodes.BST(1);
		root.left.right = new ValidationThreeNodes.BST(4);
		root.right.left = new ValidationThreeNodes.BST(6);
		root.right.right = new ValidationThreeNodes.BST(8);
		root.left.left.left = new ValidationThreeNodes.BST(0);
		root.left.right.left = new ValidationThreeNodes.BST(3);

		BST nodeOne = root;
		BST nodeTwo = root.left;
		BST nodeThree = root.left.right.left;
		boolean expected = true;
		boolean actual = new ValidationThreeNodes().validateThreeNodes(nodeOne, nodeTwo, nodeThree);
		assertTrue(expected == actual);

	}

}
