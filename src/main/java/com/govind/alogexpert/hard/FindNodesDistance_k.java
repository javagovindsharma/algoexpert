package com.govind.alogexpert.hard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FindNodesDistance_k {
	static class BinaryTree {
		public int value;
		public BinaryTree left = null;
		public BinaryTree right = null;

		public BinaryTree(int value) {
			this.value = value;
		}
	}

	static class Pair<U, V> {
		public final U first;
		public final V second;

		private Pair(U first, V second) {
			this.first = first;
			this.second = second;
		}
	}

	public ArrayList<Integer> findNodesDistanceK1(BinaryTree tree, int target, int k) {
		HashMap<Integer, BinaryTree> nodeToParents = new HashMap<Integer, BinaryTree>();
		populateNodesToParents(tree, nodeToParents, null);
		BinaryTree targetNode = getNodeFromValue(target, tree, nodeToParents);
		return breadthFirstSearchForNodesDistanceK(targetNode, nodeToParents, k);
	}

	private ArrayList<Integer> breadthFirstSearchForNodesDistanceK(BinaryTree targetNode,
			HashMap<Integer, BinaryTree> nodeToParents, int k) {
		Queue<Pair<BinaryTree, Integer>> queue = new LinkedList<Pair<BinaryTree, Integer>>();
		queue.offer(new Pair<BinaryTree, Integer>(targetNode, 0));

		HashSet<Integer> seen = new HashSet<Integer>(targetNode.value);
		seen.add(targetNode.value);

		while (queue.size() > 0) {
			Pair<BinaryTree, Integer> vals = queue.poll();
			BinaryTree currentNode = vals.first;
			int distanceFromTarget = vals.second;

			if (distanceFromTarget == k) {
				ArrayList<Integer> nodeDistanceK = new ArrayList<Integer>();
				for (Pair<BinaryTree, Integer> pair : queue) {
					nodeDistanceK.add(pair.first.value);
				}
				nodeDistanceK.add(currentNode.value);
				return nodeDistanceK;
			}

			List<BinaryTree> connectedNodes = new ArrayList<BinaryTree>();
			connectedNodes.add(currentNode.left);
			connectedNodes.add(currentNode.right);
			connectedNodes.add(nodeToParents.get(currentNode.value));

			for (BinaryTree node : connectedNodes) {
				if (node == null)
					continue;
				if (seen.contains(node.value))
					continue;
				seen.add(node.value);
				queue.add(new Pair<BinaryTree, Integer>(node, distanceFromTarget + 1));
			}
		}
		return new ArrayList<Integer>();
	}

	private BinaryTree getNodeFromValue(int value, BinaryTree tree, HashMap<Integer, BinaryTree> nodeToParents) {
		if (tree.value == value)
			return tree;

		BinaryTree nodeParent = nodeToParents.get(value);
		if (nodeParent.left != null && nodeParent.left.value == value)
			return nodeParent.left;
		return nodeParent.right;
	}

	private void populateNodesToParents(BinaryTree node, HashMap<Integer, BinaryTree> nodeToParents,
			BinaryTree parent) {
		if (node != null) {
			nodeToParents.put(node.value, parent);
			populateNodesToParents(node.left, nodeToParents, node);
			populateNodesToParents(node.right, nodeToParents, node);
		}

	}

	// solution 2
	public ArrayList<Integer> findNodesDistanceK(BinaryTree tree, int target, int k) {
		ArrayList<Integer> nodeDistanceK = new ArrayList<Integer>();
		findDistanceFromNodeToTarget(tree, target, k, nodeDistanceK);
		return nodeDistanceK;
	}

	private int findDistanceFromNodeToTarget(BinaryTree node, int target, int k, ArrayList<Integer> nodeDistanceK) {

		if (node == null)
			return -1;

		if (node.value == target) {
			addSubtreNodeAtDistanceK(node, 0, k, nodeDistanceK);
			return 1;
		}

		int leftDistance = findDistanceFromNodeToTarget(node.left, target, k, nodeDistanceK);
		int rightDistance = findDistanceFromNodeToTarget(node.right, target, k, nodeDistanceK);

		if (leftDistance == k || rightDistance == k)
			nodeDistanceK.add(node.value);

		if (leftDistance != -1) {
			addSubtreNodeAtDistanceK(node.right, leftDistance + 1, k, nodeDistanceK);
			return leftDistance + 1;
		}
		if (rightDistance != -1) {
			addSubtreNodeAtDistanceK(node.left, rightDistance + 1, k, nodeDistanceK);
			return rightDistance + 1;
		}
		return -1;
	}

	private void addSubtreNodeAtDistanceK(BinaryTree node, int distance, int k, ArrayList<Integer> nodeDistanceK) {

		if (node == null)
			return;

		if (distance == k) {
			nodeDistanceK.add(node.value);
		} else {
			addSubtreNodeAtDistanceK(node.left, distance + 1, k, nodeDistanceK);

			addSubtreNodeAtDistanceK(node.right, distance + 1, k, nodeDistanceK);
		}

	}

	public static void main(String[] args) {
		FindNodesDistance_k.BinaryTree root = new FindNodesDistance_k.BinaryTree(1);
		root.left = new FindNodesDistance_k.BinaryTree(2);
		root.right = new FindNodesDistance_k.BinaryTree(3);
		root.left.left = new FindNodesDistance_k.BinaryTree(4);
		root.left.right = new FindNodesDistance_k.BinaryTree(5);
		root.right.right = new FindNodesDistance_k.BinaryTree(6);
		root.right.right.left = new FindNodesDistance_k.BinaryTree(7);
		root.right.right.right = new FindNodesDistance_k.BinaryTree(8);
		int target = 3;
		int k = 2;
		List<Integer> expected = new ArrayList<Integer>(Arrays.asList(2, 7, 8));
		List<Integer> actual = new FindNodesDistance_k().findNodesDistanceK(root, target, k);
		Collections.sort(actual);
		assertTrue(expected.equals(actual));

	}

}
