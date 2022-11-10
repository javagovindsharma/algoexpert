package com.govind.alogexpert.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class InvertedBisection {

	public static LinkedList invertedBisection(LinkedList head) {		
		
		int length = getLinkedListLength(head);
		if (length <= 3)
			return head;

		LinkedList firsthalfTrail = getMiddleNode(head, length);
		LinkedList middleNode = null;
		LinkedList secondHalfhead = null;

		if (length % 2 == 0) {
			secondHalfhead = firsthalfTrail.next;
		} else {
			middleNode = firsthalfTrail.next;
			secondHalfhead = firsthalfTrail.next.next;
		}
		firsthalfTrail.next = null;
		reverse(head);
		LinkedList reversedSecondHalfHead = reverse(secondHalfhead);

		if(middleNode==null) {
			head.next=reversedSecondHalfHead;
		}else {
			head.next=middleNode;
			middleNode.next=reversedSecondHalfHead;
		}
		return firsthalfTrail;
	}

	static int getLinkedListLength(LinkedList head) {
		int length = 0;
		LinkedList currentNode = head;
		while (currentNode != null) {
			currentNode = currentNode.next;
			length++;
		}
		return length;
	}

	static LinkedList getMiddleNode(LinkedList head, int length) {
		int halfLength = length / 2;
		int currentPosition = 1;
		LinkedList CurrentNode = head;
		while (currentPosition != halfLength) {
			CurrentNode = CurrentNode.next;
			currentPosition++;
		}
		return CurrentNode;
	}

	static LinkedList reverse(LinkedList head) {
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

	// This is an input class. Do not edit.
	static class LinkedList {
		int value;
		LinkedList next = null;

		public LinkedList(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		LinkedList test = addMany(new InvertedBisection.LinkedList(0), new int[] { 1, 2, 3, 4, 5 });
		InvertedBisection.invertedBisection(test);
		List<Integer> result = getNodesInArray(InvertedBisection.invertedBisection(test));
		List<Integer> expected = getNodesInArray(
				addMany(new InvertedBisection.LinkedList(2), new int[] { 1, 0, 5, 4, 3 }));
		assertTrue(result.equals(expected));
	}

	public static InvertedBisection.LinkedList addMany(InvertedBisection.LinkedList linkedList, int[] values) {
		LinkedList current = linkedList;
		while (current.next != null) {
			current = current.next;
		}
		for (int value : values) {
			current.next = new InvertedBisection.LinkedList(value);
			current = current.next;
		}
		return linkedList;
	}

	public static List<Integer> getNodesInArray(InvertedBisection.LinkedList linkedList) {
		List<Integer> nodes = new ArrayList<Integer>();
		LinkedList current = linkedList;
		while (current != null) {
			nodes.add(current.value);
			current = current.next;
		}
		return nodes;
	}
}
