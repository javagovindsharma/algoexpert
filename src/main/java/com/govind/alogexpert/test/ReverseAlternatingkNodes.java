package com.govind.alogexpert.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReverseAlternatingkNodes {

	static class LinkedList {
		int value;
		LinkedList next = null;

		public LinkedList(int value) {
			this.value = value;
		}
	}

	// solution 1
	public static LinkedList reverseAlternatingKNodes1(LinkedList head, int k) {
		LinkedList finalHead = null;
		boolean isGroupToReverse = true;
		int runningK = 1;

		LinkedList previousGroupTail = null;
		LinkedList currentGroupHead = head;
		LinkedList currentNode = head;

		while (currentNode != null) {
			boolean shouldReverse = isGroupToReverse && (runningK == k || currentNode.next == null);
			if (!shouldReverse) {
				if (runningK == k) {
					runningK = 1;
					isGroupToReverse = true;
					previousGroupTail = currentNode;
					currentGroupHead = currentNode.next;
				} else {
					runningK += 1;
				}
				currentNode = currentNode.next;
				continue;
			}
			runningK = 1;
			isGroupToReverse = false;
			LinkedList nextNode = currentNode.next;
			currentNode.next = null;
			LinkedList reverseGroupHead = revrseLinkedList(currentGroupHead);
			LinkedList reverseGroupTail = currentGroupHead;
			reverseGroupTail.next = nextNode;

			if (previousGroupTail == null) {
				finalHead = reverseGroupHead;
			} else {
				previousGroupTail.next = reverseGroupHead;
			}
			currentNode = nextNode;
			currentGroupHead = nextNode;
			previousGroupTail = reverseGroupTail;
		}
		return finalHead;
	}

	private static LinkedList revrseLinkedList(LinkedList head) {
		LinkedList previousNode = null;
		LinkedList currentNode = head;
		while (currentNode != null) {
			LinkedList nextNode = currentNode.next;
			currentNode.next = previousNode;
			previousNode = currentNode;
			currentNode = nextNode;
		}

		return previousNode;
	}

	// solution 2
	public static LinkedList reverseAlternatingKNodes(LinkedList head, int k) {
		LinkedList finalHead = null;
		LinkedList previousNode = null;
		LinkedList currentNode = head;

		while (currentNode != null) {
			LinkedList[] reversed = reverseKNodes(currentNode,k);
			LinkedList reversedGroupHead=reversed[0];
			LinkedList nextNode=reversed[1];
			
			currentNode.next=nextNode;
			currentNode=nextNode;
			if(previousNode==null) {
				finalHead=reversedGroupHead;
			}else {
				previousNode.next=reversedGroupHead;
			}
			int skippedNodesCount=0;
			while(currentNode!=null && skippedNodesCount<k) {
				previousNode=currentNode;
				currentNode=currentNode.next;
				skippedNodesCount+=1;
			}
		}
		return finalHead;
	}

	private static LinkedList[] reverseKNodes(LinkedList head, int k) {
		int reverseNodesCount=0;
		LinkedList previousNode=null;
		LinkedList currentNode=head;
		while(currentNode!=null && reverseNodesCount<k) {
			LinkedList nextNode=currentNode.next;
			currentNode.next=previousNode;
			previousNode=currentNode;
			currentNode=nextNode;
			reverseNodesCount+=1;
		}
		return new LinkedList[] { previousNode, currentNode};
	}

	public static void main(String[] args) {
		LinkedList linkedlist = new ReverseAlternatingkNodes.LinkedList(1);
		addMany(linkedlist, Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14));
		int k = 3;
		List<Integer> expected = Arrays.asList(3, 2, 1, 4, 5, 6, 9, 8, 7, 10, 11, 12, 14, 13);
		List<Integer> actual = getNodesInArray(ReverseAlternatingkNodes.reverseAlternatingKNodes(linkedlist, k));
		assertTrue(expected.equals(actual));

	}

	public static void addMany(ReverseAlternatingkNodes.LinkedList head, List<Integer> values) {
		LinkedList current = head;
		for (int value : values) {
			current.next = new ReverseAlternatingkNodes.LinkedList(value);
			current = current.next;
		}
	}

	public static List<Integer> getNodesInArray(ReverseAlternatingkNodes.LinkedList head) {
		List<Integer> nodes = new ArrayList<Integer>();
		LinkedList current = head;
		while (current != null) {
			nodes.add(current.value);
			current = current.next;
		}
		return nodes;
	}

}
