package com.govind.alogexpert.medum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reconstruct {

	static class BST {
		public int value;
		public BST left = null;
		public BST right = null;

		public BST(int value) {
			this.value = value;
		}
	}

	//solution 1
	public BST reconstructBstSolution1(List<Integer> preOrderTraversalValues) {
		if(preOrderTraversalValues.size()==0) {
			return null;
		}
		
		int currentValue=preOrderTraversalValues.get(0);
		int rightSubtreeRootIdx=preOrderTraversalValues.size();
		
		for(int idx=1;idx<preOrderTraversalValues.size();idx++) {
			  int value=preOrderTraversalValues.get(idx);
			  if(value>=currentValue) {
				  rightSubtreeRootIdx=idx;
				  break;
			  }
		}
		BST leftSubtree=reconstructBstSolution1(preOrderTraversalValues.subList(1, rightSubtreeRootIdx));
		BST rightSubtree=reconstructBstSolution1(preOrderTraversalValues.subList(rightSubtreeRootIdx, preOrderTraversalValues.size()));
		BST bst=new BST(currentValue);
		bst.left=leftSubtree;
		bst.right=rightSubtree;
		return bst;
	}
	
	
	// solution 2
	static class TreeInfo{
		 public int rootIdx;
		 
		 public TreeInfo(int rootIdx) {
			 this.rootIdx=rootIdx;
		 }
	}
	
	public BST reconstructBst(List<Integer> preOrderTraversalValues) {
		TreeInfo treeInfo=new TreeInfo(0);
		return reconstructBstFromRange(Integer.MIN_VALUE,Integer.MAX_VALUE,preOrderTraversalValues,treeInfo);
	}
	
	public BST reconstructBstFromRange(int lowerBound,int upperBound,List<Integer> preOrderTraversalValues,TreeInfo currentSubtreeInfo) {
		if(currentSubtreeInfo.rootIdx==preOrderTraversalValues.size()) {
		  return null;	
		}
		int rootValue=preOrderTraversalValues.get(currentSubtreeInfo.rootIdx);
		if(rootValue<lowerBound || rootValue>=upperBound) {
			return null;
		}
		currentSubtreeInfo.rootIdx+=1;
		BST leftSubtree=reconstructBstFromRange(lowerBound, rootValue, preOrderTraversalValues, currentSubtreeInfo);
		BST rightSubtree=reconstructBstFromRange(lowerBound, upperBound, preOrderTraversalValues, currentSubtreeInfo);
		
		BST bst=new BST(rootValue);
		bst.left=leftSubtree;
		bst.right=rightSubtree;
		return bst;
	}

	
	public static List<Integer> getDfsOrder(BST node, List<Integer> values) {
	    values.add(node.value);
	    if (node.left != null) {
	      getDfsOrder(node.left, values);
	    }
	    if (node.right != null) {
	      getDfsOrder(node.right, values);
	    }
	    return values;
	  }
	
	public static void main(String[] args) {
		List<Integer> preOrderTraversalValues = new ArrayList<Integer>(Arrays.asList(10, 4, 2, 1, 3, 17, 19, 18));
		BST tree = new BST(10);
		tree.left = new BST(4);
		tree.left.left = new BST(2);
		tree.left.left.left = new BST(1);
		tree.left.right = new BST(3);
		tree.right = new BST(17);
		tree.right.right = new BST(19);
		tree.right.right.left = new BST(18);
		List<Integer> expected = getDfsOrder(tree, new ArrayList<Integer>());
		BST actual = new Reconstruct().reconstructBst(preOrderTraversalValues);
		List<Integer> actualValues = getDfsOrder(actual, new ArrayList<Integer>());
		System.out.println(expected.equals(actualValues));

	}
}
