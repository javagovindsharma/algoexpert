package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NodeSwap {

	public static class LinkedList {
		public int value;
		public LinkedList next;

		public LinkedList(int value) {
			this.value = value;
			this.next = null;
		}
	}
// solution 1
	public LinkedList nodeSwap1(LinkedList head) {
		if(head==null|| head.next==null) {
			return head;
		}
		
		LinkedList nextNode=head.next;
		head.next=nodeSwap(head.next.next);
		nextNode.next=head;
		return nextNode;
	}
	// solution 2
		public LinkedList nodeSwap(LinkedList head) {
			LinkedList tempNode=new LinkedList(0);
			tempNode.next=head;
			
			LinkedList prevNode=tempNode;
			while(prevNode.next!=null && prevNode.next.next!=null) {
				LinkedList firstNode=prevNode.next;
				LinkedList secondNode=prevNode.next.next;
				
				firstNode.next=secondNode.next;
				secondNode.next=firstNode;
				prevNode.next=secondNode;
				
				prevNode=firstNode;
			}
			return tempNode.next;
		}

	public static void main(String[] args) {
		NodeSwap.LinkedList linkedList = new NodeSwap.LinkedList(0);
		addMany(linkedList, new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5)));
		List<Integer> expectedNodes = new ArrayList<Integer>(Arrays.asList(1, 0, 3, 2, 5, 4));
		LinkedList actual = new NodeSwap().nodeSwap(linkedList);
		assertTrue(getNodesInArray(actual).equals(expectedNodes));

	}

	public static NodeSwap.LinkedList addMany(NodeSwap.LinkedList ll, List<Integer> values) {
		NodeSwap.LinkedList current = ll;
		while (current.next != null) {
			current = current.next;
		}
		for (int value : values) {
			current.next = new NodeSwap.LinkedList(value);
			current = current.next;
		}
		return ll;
	}

	public static List<Integer> getNodesInArray(NodeSwap.LinkedList ll) {
		List<Integer> nodes = new ArrayList<Integer>();
		NodeSwap.LinkedList current = ll;
		while (current != null) {
			nodes.add(current.value);
			current = current.next;
		}
		return nodes;
	}

}
