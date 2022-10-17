package com.govind.alogexpert.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RemoveDuplicateFromLinkedList {
	public static class LinkedList {
		public int value;
		public LinkedList next;

		public LinkedList(int value) {
			this.value = value;
			this.next = null;
		}
	}

	public LinkedList removeDuplicatesFromLinkedList1(LinkedList linkedList) {
		LinkedList currentNode = linkedList;
		while (currentNode != null) {
			LinkedList nextDistinctNode = currentNode.next;
			while (nextDistinctNode != null && nextDistinctNode.value == currentNode.value) {
				nextDistinctNode = nextDistinctNode.next;
			}
			currentNode.next = nextDistinctNode;
			currentNode = nextDistinctNode;
		}
		return linkedList;
	}

	public static LinkedList removeDuplicatesFromLinkedList(LinkedList linkedList) {
		Set<Integer> set = new HashSet<Integer>();
		while (linkedList != null) {
			set.add(linkedList.value);
			linkedList = linkedList.next;
		}
		Iterator<Integer> itr = set.iterator();
	//	LinkedList list1 = new LinkedList(0);
		while (itr.hasNext()) {
			int vt=itr.next();
			LinkedList list = new LinkedList(vt);
			System.out.println(vt);
			linkedList = list;
			linkedList.next = list;
			linkedList=linkedList.next;
		}
		System.out.println(linkedList.toString());
		return linkedList;
	}

	public static void main(String[] args) {

		RemoveDuplicateFromLinkedList.LinkedList input = new RemoveDuplicateFromLinkedList.LinkedList(1);
		addMany(input, new ArrayList<Integer>(Arrays.asList(1, 3, 4, 4, 4, 5, 6, 6)));
	//	LinkedList list=removeDuplicatesFromLinkedList(input);
	//	List<Integer> li= getNodesInArray(list);
		
	}
	
	public static RemoveDuplicateFromLinkedList.LinkedList addMany(RemoveDuplicateFromLinkedList.LinkedList ll, List<Integer> values) {
		RemoveDuplicateFromLinkedList.LinkedList current = ll;
	    while (current.next != null) {
	      current = current.next;
	    }
	    for (int value : values) {
	      current.next = new RemoveDuplicateFromLinkedList.LinkedList(value);
	      current = current.next;
	    }
	    return ll;
	  }
	public static  List<Integer> getNodesInArray(RemoveDuplicateFromLinkedList.LinkedList ll) {
	    List<Integer> nodes = new ArrayList<Integer>();
	    RemoveDuplicateFromLinkedList.LinkedList current = ll;
	    while (current != null) {
	      nodes.add(current.value);
	      current = current.next;
	    }
	    return nodes;
	  }
}
