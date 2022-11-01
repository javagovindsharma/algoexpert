package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

public class LinkedListPalindrome {

	public static class LinkedList {
		public int value;
		public LinkedList next;

		public LinkedList(int value) {
			this.value = value;
			this.next = null;
		}
	}

	// solution 1
	public boolean linkedListPalindrome1(LinkedList head) {
		LinkedListInfo isPalindromeResults = isPalindrome(head, head);
		return isPalindromeResults.outerNodesAreEqual;
	}

	private LinkedListInfo isPalindrome(LinkedList leftNode, LinkedList rightNode) {
		if (rightNode == null)
			return new LinkedListInfo(true, leftNode);

		LinkedListInfo recursiveCallResults = isPalindrome(leftNode, rightNode.next);
		LinkedList leftNodeToCompare = recursiveCallResults.leftNodeToCompare;
		boolean outerNodesAreEqual = recursiveCallResults.outerNodesAreEqual;

		boolean recursiveIsEqual = outerNodesAreEqual && (leftNodeToCompare.value == rightNode.value);
		LinkedList nextLeftNodeToCompare = leftNodeToCompare.next;
		return new LinkedListInfo(recursiveIsEqual, nextLeftNodeToCompare);
	}

	static class LinkedListInfo {
		boolean outerNodesAreEqual;
		LinkedList leftNodeToCompare;

		public LinkedListInfo(boolean outerNodesAreEqual, LinkedList leftNodeToCompare) {
			this.outerNodesAreEqual = outerNodesAreEqual;
			this.leftNodeToCompare = leftNodeToCompare;
		}
	}

	// solution 2
	public boolean linkedListPalindrome(LinkedList head) {
		LinkedList slowNode=head;
		LinkedList fastNode=head;
		
		while(fastNode!=null && fastNode.next!=null) {
			slowNode=slowNode.next;
			fastNode=fastNode.next.next;
		}
		
		LinkedList reversedSecondHalfNode=reverseLinkedList(slowNode);
		LinkedList firstHalfNode=head;
		
		while(reversedSecondHalfNode!=null) {
			if(reversedSecondHalfNode.value!=firstHalfNode.value) return false;
			reversedSecondHalfNode=reversedSecondHalfNode.next;
			firstHalfNode=firstHalfNode.next;
			
		}
		
		return true;
	}

	private LinkedList reverseLinkedList(LinkedList head) {
		LinkedList previousNode=null;
		LinkedList currentNode=head;
		
		while(currentNode!=null){
			LinkedList nextNode=currentNode.next;
			currentNode.next=previousNode;
			previousNode=currentNode;
			currentNode=nextNode;
		}
		
		return previousNode;
	}

	public static void main(String[] args) {
		LinkedList head = new LinkedListPalindrome.LinkedList(0);
		head.next = new LinkedListPalindrome.LinkedList(1);
		head.next.next = new LinkedListPalindrome.LinkedList(2);
		head.next.next.next = new LinkedListPalindrome.LinkedList(2);
		head.next.next.next.next = new LinkedListPalindrome.LinkedList(1);
		head.next.next.next.next.next = new LinkedListPalindrome.LinkedList(0);
		boolean expected = true;
		boolean actual = new LinkedListPalindrome().linkedListPalindrome(head);
		assertTrue(expected == actual);

	}

}
