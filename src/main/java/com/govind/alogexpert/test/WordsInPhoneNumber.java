package com.govind.alogexpert.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordsInPhoneNumber {

	public static Map<Character, List<Character>> DIGIT_LETTERS = new HashMap<Character, List<Character>>();
	static {
		DIGIT_LETTERS.put('0', new ArrayList<Character>());
		DIGIT_LETTERS.put('1', new ArrayList<Character>());
		DIGIT_LETTERS.put('2', new ArrayList<Character>(Arrays.asList('a', 'b', 'c')));
		DIGIT_LETTERS.put('3', new ArrayList<Character>(Arrays.asList('d', 'e', 'f')));
		DIGIT_LETTERS.put('4', new ArrayList<Character>(Arrays.asList('g', 'h', 'i')));
		DIGIT_LETTERS.put('5', new ArrayList<Character>(Arrays.asList('j', 'k', 'l')));
		DIGIT_LETTERS.put('6', new ArrayList<Character>(Arrays.asList('m', 'n', 'o')));
		DIGIT_LETTERS.put('7', new ArrayList<Character>(Arrays.asList('p', 'q', 'r', 's')));
		DIGIT_LETTERS.put('8', new ArrayList<Character>(Arrays.asList('t', 'u', 'v')));
		DIGIT_LETTERS.put('9', new ArrayList<Character>(Arrays.asList('w', 'x', 'y', 'z')));

	}

	public static List<String> wordsInPhoneNumber(String phoneNumber, String[] words) {
		Trie wordTrie = getWordTrie(words);
		Set<String> finalWords = new HashSet<String>();
		for (int i = 0; i < phoneNumber.length(); i++) {
			exploreTrie(phoneNumber, i, wordTrie.root, finalWords);
		}
		return new ArrayList<String>(finalWords);
	}

	private static Trie getWordTrie(String[] words) {
		Trie wordTrie = new Trie();
		for (String word : words) {
			wordTrie.insert(word);
		}
		return wordTrie;
	}

	private static void exploreTrie(String phoneNumber, int digitIdx, TrieNode trieNode, Set<String> finalWords) {
		if(trieNode.children.containsKey('*')) {
			finalWords.add(trieNode.word);
		}
		
		if(digitIdx==phoneNumber.length()) return;
		char currentDigit=phoneNumber.charAt(digitIdx);
		List<Character>  possibleLetters=DIGIT_LETTERS.get(currentDigit);		
 
		for(Character letter:possibleLetters) {
			if(!trieNode.children.containsKey(letter)) continue;
			exploreTrie(phoneNumber, digitIdx+1, trieNode.children.get(letter), finalWords);
		}
		
		
	}

	static class TrieNode {
		Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
		String word = "";
	}

	static class Trie {
		TrieNode root;
		char endSymbol;

		public Trie() {
			this.root = new TrieNode();
			this.endSymbol = '*';
		}

		public void insert(String word) {
			TrieNode node = this.root;
			for (int i = 0; i < word.length(); i++) {
				char letter = word.charAt(i);
				if (!node.children.containsKey(letter)) {
					TrieNode newNode = new TrieNode();
					node.children.put(letter, newNode);
				}
				node.children.put(this.endSymbol, null);
				node.word = word;
			}
		}

		public static void main(ArrayList<Character> args) {
			String phoneNumber = "3662277";

			String[] words = new String[] { "foo", "bar", "baz", "foobar", "emo", "cap", "car", "cat" };
			ArrayList<String> expected = new ArrayList<String>(
					Arrays.asList("bar", "cap", "car", "emo", "foo", "foobar"));

		}

		public static boolean outOfOrderEqual(List<String> a, List<String> b) {
			if (a.size() != b.size()) {
				return false;
			}
			return a.containsAll(b) && b.containsAll(a);
		}

	}
}
