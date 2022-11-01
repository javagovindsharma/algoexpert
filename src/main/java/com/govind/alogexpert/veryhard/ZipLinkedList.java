package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZipLinkedList {

	public static class LinkedList {
		public int value;
		public LinkedList next;

		public LinkedList(int value) {
			this.value = value;
			this.next = null;
		}
	}

	public LinkedList zipLinkedList(LinkedList linkedList) {
		if (linkedList.next == null || linkedList.next.next == null) {
			return linkedList;
		}

		LinkedList firstHalfHead = linkedList;
		LinkedList secondHalfHead = splitLinkedList(linkedList);

		LinkedList reversedSecondHalfHead = reversedLinkedList(secondHalfHead);

		return interweaveLinkedLists(firstHalfHead, reversedSecondHalfHead);
	}

	private LinkedList splitLinkedList(LinkedList linkedList) {
		LinkedList slowIterator = linkedList;
		LinkedList fastIterator = linkedList;

		while (fastIterator != null && fastIterator.next != null) {
			slowIterator = slowIterator.next;
			fastIterator = fastIterator.next.next;
		}

		LinkedList secondHalfHead = slowIterator.next;
		slowIterator.next = null;

		return secondHalfHead;
	}

	private LinkedList interweaveLinkedLists(LinkedList linkedList1, LinkedList linkedList2) {
		LinkedList linkedList1Iterator = linkedList1;
		LinkedList linkedList2Iterator = linkedList2;

		while (linkedList1Iterator != null && linkedList2Iterator != null) {
			LinkedList firstHalfIteratorNext = linkedList1Iterator.next;
			LinkedList secondHalfIteratorNext = linkedList2Iterator.next;

			linkedList1Iterator.next = linkedList2Iterator;
			linkedList2Iterator.next = firstHalfIteratorNext;

			linkedList1Iterator = firstHalfIteratorNext;
			linkedList2Iterator = secondHalfIteratorNext;
		}
		return linkedList1;
	}

	private LinkedList reversedLinkedList(LinkedList linkedList) {
		LinkedList previousNode = null;
		LinkedList currentNode = linkedList;

		while (currentNode != null) {
			LinkedList nextNode = currentNode.next;
			currentNode.next = previousNode;
			previousNode = currentNode;
			currentNode = nextNode;
		}
		return previousNode;
	}

	public static void main(String[] args) {
		ZipLinkedList.TestLinkedList head = new ZipLinkedList.TestLinkedList(1);
		head.addMany(new int[] { 2, 3, 4, 5, 6 });
		List<Integer> expected = new ArrayList<Integer>(Arrays.asList(1, 6, 2, 5, 3, 4));
		List<Integer> actual = getNodesInArray(new ZipLinkedList().zipLinkedList(head));
		assertTrue(expected.equals(actual));

	}

	public static List<Integer> getNodesInArray(ZipLinkedList.LinkedList linkedList) {
		List<Integer> nodes = new ArrayList<Integer>();
		LinkedList current = linkedList;
		while (current != null) {
			nodes.add(current.value);
			current = current.next;
		}
		return nodes;
	}

	static class TestLinkedList extends ZipLinkedList.LinkedList {
		public TestLinkedList(int value) {
			super(value);
		}

		public void addMany(int[] values) {
			ZipLinkedList.LinkedList current = this;
			while (current.next != null) {
				current = current.next;
			}
			for (int value : values) {
				current.next = new ZipLinkedList.LinkedList(value);
				current = current.next;
			}
		}
	}
}
