package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RearrangeLinkedList {

	public static LinkedList rearrangeLinkedList(LinkedList head, int k) {
		LinkedList smallerListHead = null;
		LinkedList smallerListTail = null;
		LinkedList equalListHead = null;
		LinkedList equalListTail = null;
		LinkedList greaterListHead = null;
		LinkedList greaterListTail = null;
		
		LinkedList node=head;
		
		while(node!=null) {
			if(node.value<k) {
				LinkedListPair smallerList=growLinkedList(smallerListHead,smallerListTail,node);
				smallerListHead=smallerList.head;
				smallerListTail=smallerList.tail;
			}else if(node.value>k) {
				LinkedListPair greaterList=growLinkedList(greaterListHead,greaterListTail,node);
				greaterListHead=greaterList.head;
				greaterListTail=greaterList.tail;
			}else {
				LinkedListPair equalList=growLinkedList(equalListHead,equalListTail,node);
				equalListHead=equalList.head;
				equalListTail=equalList.tail;			
			}
			
			LinkedList prevNode=node;
			node=node.next;
			prevNode.next=null;
		}
		
		LinkedListPair firstPair=connectLinkedLists(smallerListHead,smallerListTail,equalListHead,equalListTail);
		LinkedListPair finalPair=connectLinkedLists(firstPair.head,firstPair.tail,greaterListHead,greaterListTail);
		return finalPair.head;
	}
	
	static LinkedListPair growLinkedList(LinkedList head,LinkedList tail,LinkedList node) {
		LinkedList newHead=head;
		LinkedList newTail=node;
		
		if(newHead==null) newHead=node;
		if(tail!=null) tail.next=node;
		return new LinkedListPair(newHead,newTail);
	}
	
	static LinkedListPair connectLinkedLists(LinkedList headOne,LinkedList tailOne,LinkedList headTwo,LinkedList tailTwo) {
		LinkedList newHead=headOne==null?headTwo:headOne;
		LinkedList newTail=tailTwo==null?tailOne:tailTwo;
		
		if(tailOne!=null) tailOne.next=headTwo;
		return new LinkedListPair(newHead,newTail);
	}
	
    static class LinkedListPair{
    	LinkedList head;
    	LinkedList tail;
		public LinkedListPair(LinkedList head, LinkedList tail) {
			this.head = head;
			this.tail = tail;
		}   	
    }
    
	static class LinkedList {
		public int value;
		public LinkedList next;

		public LinkedList(int value) {
			this.value = value;
			next = null;
		}
	}

	public static void main(String[] args) {
		LinkedList head = new RearrangeLinkedList.LinkedList(3);
		head.next = new RearrangeLinkedList.LinkedList(0);
		head.next.next = new RearrangeLinkedList.LinkedList(5);
		head.next.next.next = new RearrangeLinkedList.LinkedList(2);
		head.next.next.next.next = new RearrangeLinkedList.LinkedList(1);
		head.next.next.next.next.next = new RearrangeLinkedList.LinkedList(4);
		LinkedList result = RearrangeLinkedList.rearrangeLinkedList(head, 3);
		List<Integer> array = linkedListToArray(result);

		List<Integer> expected = Arrays.asList(new Integer[] { 0, 2, 1, 3, 5, 4 });
		assertTrue(expected.equals(array));

	}

	public static List<Integer> linkedListToArray(RearrangeLinkedList.LinkedList head) {
		List<Integer> array = new ArrayList<Integer>();
		LinkedList current = head;
		while (current != null) {
			array.add(current.value);
			current = current.next;
		}
		return array;
	}

}
