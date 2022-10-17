package com.govind.alogexpert.hard;

import static org.junit.Assert.assertTrue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MultiStringSearch {
	// solution 3
	public static List<Boolean> multiStringSearch(String bigString, String[] smallStrings) {
		Trie trie = new Trie();
		for (String smallString : smallStrings) {
			trie.insert(smallString);
		}
		Set<String> containedStrings = new HashSet<String>();
		for (int i = 0; i < bigString.length(); i++) {
			findSmallStringsIn(bigString, i, trie, containedStrings);
		}
		List<Boolean> solution = new ArrayList<Boolean>();
		for (String str : smallStrings) {
			solution.add(containedStrings.contains(str));
		}
		return solution;
	}

	private static void findSmallStringsIn(String str, int startIdx, Trie trie, Set<String> containedStrings) {
		TrieNode currentNode=trie.root;
		for(int i=startIdx;i<str.length();i++) {
			char currentChar=str.charAt(i);
			if(!currentNode.children.containsKey(currentChar)) {
				break;
			}
			currentNode=currentNode.children.get(currentChar);
			if(currentNode.children.containsKey(trie.endSymbol)) {
				containedStrings.add(currentNode.word);
			}
		}
		
	}
	
	static class TrieNode{
		 Map<Character,TrieNode> children=new HashMap<Character,TrieNode>();
		 String word;
	}
	
	static class Trie{
		 TrieNode root=new TrieNode();
		 char endSymbol='*';
		 
		 public void insert(String str) {
			 TrieNode node=root;
			 for(int i=0;i<str.length();i++) {
				 char letter=str.charAt(i);
				 if(!node.children.containsKey(letter)) {
					 TrieNode newNode=new TrieNode();
					 node.children.put(letter, newNode);
				 }
				 node=node.children.get(letter);
			 }
			 node.children.put(endSymbol, null);
			 node.word=str;
		 }
	}

	// solution 2
	public static List<Boolean> multiStringSearch2(String bigString, String[] smallStrings) {
		ModifiedSuffixTrie modifiedSuffixTrie = new ModifiedSuffixTrie(bigString);
		List<Boolean> solution = new ArrayList<Boolean>();
		for (String smallString : smallStrings) {
			solution.add(modifiedSuffixTrie.contains(smallString));
		}
		return solution;
	}

	static class TrieNode1 {
		Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
	}

	static class ModifiedSuffixTrie {
		TrieNode root = new TrieNode();

		public ModifiedSuffixTrie(String str) {
			populateModifiedSuffixTrieFrom(str);
		}

		private void populateModifiedSuffixTrieFrom(String str) {
			for (int i = 0; i < str.length(); i++) {
				insertSubStringStartingAt(i, str);
			}
		}

		private void insertSubStringStartingAt(int i, String str) {
			TrieNode node = root;
			for (int j = i; j < str.length(); j++) {
				char letter = str.charAt(j);
				if (!node.children.containsKey(letter)) {
					TrieNode newNode = new TrieNode();
					node.children.put(letter, newNode);
				}
				node = node.children.get(letter);
			}
		}

		public boolean contains(String str) {
			TrieNode node = root;
			for (int i = 0; i < str.length(); i++) {
				char letter = str.charAt(i);
				if (!node.children.containsKey(letter)) {
					return false;
				}
				node = node.children.get(letter);
			}
			return true;
		}
	}

	// solution 1
	public static List<Boolean> multiStringSearch1(String bigString, String[] smallStrings) {
		List<Boolean> solution = new ArrayList<Boolean>();
		;
		for (String smallString : smallStrings) {
			solution.add(isInBigString(bigString, smallString));
		}
		return solution;
	}

	private static Boolean isInBigString(String bigString, String smallString) {
		for (int i = 0; i < bigString.length(); i++) {
			if (i + smallString.length() > bigString.length()) {
				break;
			}
			if (isInBigString(bigString, smallString, i)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isInBigString(String bigString, String smallString, int startIdx) {
		int leftBigIdx = startIdx;
		int rightBigIdx = startIdx + smallString.length() - 1;
		int leftSmallIdx = 0;
		int rightSmallIdx = smallString.length() - 1;

		while (leftBigIdx <= rightBigIdx) {
			if (bigString.charAt(leftBigIdx) != smallString.charAt(leftSmallIdx)
					|| bigString.charAt(rightBigIdx) != smallString.charAt(rightSmallIdx)) {
				return false;
			}
			rightBigIdx--;
			leftBigIdx++;
			leftSmallIdx++;
			rightSmallIdx--;
		}
		return true;
	}

	@SuppressWarnings("unlikely-arg-type")
	public static void main(String[] args) {
		boolean[] expected = { true, false, true, true, false, true, false };
		List<Boolean> output = MultiStringSearch.multiStringSearch("this is a big string",
				new String[] { "this", "yo", "is", "a", "bigger", "string", "kappa" });
		assertTrue(output.equals(expected));
	}

	public boolean compare(List<Boolean> arr1, boolean[] arr2) {
		if (arr1.size() != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.size(); i++) {
			if (arr1.get(i) != arr2[i]) {
				return false;
			}
		}
		return true;
	}

}
