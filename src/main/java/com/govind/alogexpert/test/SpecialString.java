package com.govind.alogexpert.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SpecialString {
	public static List<String> specialStrings(String[] strings) {
		Trie trie = new Trie();
		for (String string : strings) {
			trie.insert(string);
		}
		return Arrays.asList(strings).stream().filter(string -> isSpecial(string, trie.root, 0, 0, trie))
				.collect(Collectors.toList());
	}

	private static boolean isSpecial(String string, TrieNode trieNode, int idx, int count, Trie trie) {
		char c=string.charAt(idx);
		if(!trieNode.chilren.containsKey(c)) return false;
		
		TrieNode nextTrieNode=trieNode.chilren.get(c);
		
		boolean atEndOfString=idx==string.length()-1;
		if(atEndOfString) return nextTrieNode.chilren.containsKey(trie.endSymbol)&& count +1 >=2;
		
		if(nextTrieNode.chilren.containsKey(trie.endSymbol)) {
			boolean restIsSpecial=isSpecial(string, trie.root, idx+1, count+1, trie);
			if(restIsSpecial) return true;
		}
		
		
		return isSpecial(string, nextTrieNode, idx+1, count, trie);
	}

	static class TrieNode {
		Map<Character, TrieNode> chilren = new HashMap<Character, TrieNode>();
	}

	static class Trie {
		TrieNode root = new TrieNode();
		char endSymbol = '*';

		public void insert(String string) {
			TrieNode currentTrieNode = root;
			for (int i = 0; i < string.length(); i++) {
				char letter = string.charAt(i);
				if (!currentTrieNode.chilren.containsKey(letter)) {
					TrieNode newNode = new TrieNode();
					currentTrieNode.chilren.put(letter, newNode);
				}
				currentTrieNode = currentTrieNode.chilren.get(letter);
			}
			currentTrieNode.chilren.put(endSymbol, null);
		}
	}

	public static void main(String[] args) {
		String[] strings = new String[] { "foobarbaz", "foo", "bar", "foobarfoo", "baz", "foobaz", "foofoofoo",
				"foobazar" };
		List<String> actual = SpecialString.specialStrings(strings);
		ArrayList<String> expected = new ArrayList<String>(
				Arrays.asList("foobarbaz", "foobarfoo", "foobaz", "foofoofoo"));
		assertEquals(expected, actual);

	}

}
