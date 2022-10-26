package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class RightSiblingTree {

	public static BinaryTree rightSiblingTree(BinaryTree root) {
		mutate(root,null,false);
		return root;
	}

	private static void mutate(BinaryTree node, BinaryTree parent, boolean isLeftChild) {
		if(node==null)return;
		
		BinaryTree left=node.left;
		BinaryTree right=node.right;
		
		mutate(left, node, true);
		
		if(parent==null) {
			node.right=null;
		}else if(isLeftChild) {
			node.right=parent.right;
		}else {
			 if(parent.right==null) {
				 node.right=null;
			 }else {
				 node.right=parent.right.left;
			 }
		}
		mutate(right, node, false);
	}

	// This is the class of the input root. Do not edit it.
	static class BinaryTree {
		int value;
		BinaryTree left = null;
		BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		RightSiblingTree.BinaryTree root = new RightSiblingTree.BinaryTree(1);
		insert(root, new int[] { 2, 3, 4, 5, 6, 7, 8, 9 });
		root.left.right.right = new RightSiblingTree.BinaryTree(10);
		root.right.left.left = new RightSiblingTree.BinaryTree(11);
		root.right.right.left = new RightSiblingTree.BinaryTree(12);
		root.right.right.right = new RightSiblingTree.BinaryTree(13);
		root.right.left.left.left = new RightSiblingTree.BinaryTree(14);
		RightSiblingTree.BinaryTree mutatedRoot = RightSiblingTree.rightSiblingTree(root);
		List<Integer> actual = getDfsOrder(mutatedRoot);
		List<Integer> expected = Arrays.asList(1, 2, 4, 8, 9, 5, 6, 11, 14, 7, 12, 13, 3, 6, 11, 14, 7, 12, 13);
		assertTrue(expected.equals(actual));

	}
	
	 public static void insert(RightSiblingTree.BinaryTree root, int[] values) {
		    insert(root, values, 0);
		  }

		  public static void insert(RightSiblingTree.BinaryTree root, int[] values, int i) {
		    if (i >= values.length) {
		      return;
		    }
		    Deque<RightSiblingTree.BinaryTree> queue = new ArrayDeque<RightSiblingTree.BinaryTree>();
		    queue.addLast(root);
		    while (queue.size() > 0) {
		      RightSiblingTree.BinaryTree current = queue.pollFirst();
		      if (current.left == null) {
		        current.left = new RightSiblingTree.BinaryTree(values[i]);
		        break;
		      }
		      queue.addLast(current.left);
		      if (current.right == null) {
		        current.right = new RightSiblingTree.BinaryTree(values[i]);
		        break;
		      }
		      queue.addLast(current.right);
		    }
		    insert(root, values, i + 1);
		  }

		  public static List<Integer> getDfsOrder(RightSiblingTree.BinaryTree tree) {
		    List<Integer> values = new ArrayList<Integer>();
		    values.add(tree.value);
		    if (tree.left != null) {
		      values.addAll(getDfsOrder(tree.left));
		    }
		    if (tree.right != null) {
		      values.addAll(getDfsOrder(tree.right));
		    }
		    return values;
		  }

}
