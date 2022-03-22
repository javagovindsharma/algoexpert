package com.govind.alogexpert.medum;

import java.util.ArrayList;

public class SumLinkedList {
	LinkedList newLinkedListHeadPointer = new LinkedList(0);
	LinkedList currentList = newLinkedListHeadPointer;

	public static class LinkedList {
		public int value;
		public LinkedList next;

		public LinkedList(int value) {
			this.value = value;
			this.next = null;
		}
	}

	public LinkedList sumOfLinkedLists1(LinkedList linkedListOne, LinkedList linkedListTwo) {
		LinkedList newLinkedListHeadPointerl = new LinkedList(0);
		LinkedList currentListl = newLinkedListHeadPointerl;
		int sum = 0;
		StringBuffer str1 = new StringBuffer();
		StringBuffer str2 = new StringBuffer();
		while (linkedListOne.value >= 0) {
			str1.append(linkedListOne.value + "");
			if (linkedListOne.next == null) {
				break;
			}
			linkedListOne = linkedListOne.next;
		}
		while (linkedListTwo.value >= 0) {
			str2.append(linkedListTwo.value + "");

			if (linkedListTwo.next == null) {
				break;
			}
			linkedListTwo = linkedListTwo.next;
		}
		sum = Integer.parseInt(str1.reverse().toString()) + Integer.parseInt(str2.reverse().toString());
		if (sum == 0) {
			LinkedList list = new LinkedList(0);
			currentListl.next = list;
			currentListl = list;
		}
		while (sum > 0) {
			int rem = sum % 10;
			LinkedList list = new LinkedList(rem);
			currentListl.next = list;
			currentListl = list;
			sum = sum / 10;
		}

		return newLinkedListHeadPointerl.next;
	}

	public LinkedList sumOfLinkedLists(LinkedList linkedListOne, LinkedList linkedListTwo) {
		int carry = 0;
		while (linkedListOne != null || linkedListTwo != null || carry != 0) {

			int valueOfOne = linkedListOne != null ? linkedListOne.value : 0;
			int valueOfTwo = linkedListTwo != null ? linkedListTwo.value : 0;
			int valueOfSum = valueOfOne + valueOfTwo + carry;
			int newValue = valueOfSum % 10;
			LinkedList linkedlist = new LinkedList(newValue);
			currentList.next = linkedlist;
			currentList = linkedlist;
			linkedListOne = linkedListOne != null ? linkedListOne.next : null;
			linkedListTwo = linkedListTwo != null ? linkedListTwo.next : null;
			carry = valueOfSum / 10;
		}

		return newLinkedListHeadPointer.next;
	}

	public static void main(String[] args) {
		SumLinkedList obj = new SumLinkedList();
		LinkedList list1 = new LinkedList(0);
		int[] value1 = new int[0];// {4,7,1};
		list1 = obj.addMany(list1, value1);
		LinkedList list2 = new LinkedList(0);
		int[] value2 = new int[0];// {4,5};
		list2 = obj.addMany(list2, value2);
		LinkedList list = obj.sumOfLinkedLists1(list1, list2);
		ArrayList<Integer> array = obj.getNodesInArray(list);
		System.out.println(array.toString());
	}

	public LinkedList addMany(LinkedList linkedList, int[] values) {
		LinkedList current = linkedList;
		while (current.next != null) {
			current = current.next;
		}
		for (int value : values) {
			current.next = new LinkedList(value);
			current = current.next;
		}
		return linkedList;
	}

	public ArrayList<Integer> getNodesInArray(LinkedList linkedList) {
		ArrayList<Integer> nodeValues = new ArrayList<Integer>();
		LinkedList current = linkedList;
		while (current != null) {
			nodeValues.add(current.value);
			current = current.next;
		}
		return nodeValues;
	}
}
