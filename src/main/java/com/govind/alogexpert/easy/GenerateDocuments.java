package com.govind.alogexpert.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GenerateDocuments {

	public static boolean generateDocument2(String characters, String document) {

		for (int idx = 0; idx < document.length(); idx++) {
			char character = document.charAt(idx);
			int characterFrequency = countCharacterFrequency2(character, document);
			int documentFrequency = countCharacterFrequency2(character, characters);
			if (documentFrequency > characterFrequency) {
				return false;
			}
		}
		return true;
	}

	private static int countCharacterFrequency2(char character, String document) {
		int frequency = 0;
		for (int i = 0; i < document.length(); i++) {
			char c = document.charAt(i);
			if (c == character) {
				frequency += 1;
			}
		}
		return frequency;
	}

	public static boolean generateDocument(String characters, String document) {
		Set<Character> alreadyCounted = new HashSet<Character>();
		for (int idx = 0; idx < document.length(); idx++) {
			char character = document.charAt(idx);
			if (alreadyCounted.contains(character)) {
				continue;
			}
			int documentFrequency = countCharacterFrequency(character, document);
			int charactersFrequency = countCharacterFrequency(character, characters);
			if (documentFrequency > charactersFrequency) {
				return false;
			}
			alreadyCounted.add(character);
		}
		return true;
	}

	public static int countCharacterFrequency(char character, String target) {
		int frequency = 0;
		for (int idx = 0; idx < target.length(); idx++) {
			char c = target.charAt(idx);
			if (c == character) {
				frequency += 1;
			}
		}
		return frequency;
	}

	public static boolean generateDocument3(String characters, String document) {

		Map<Character, Integer> characterCount = new HashMap<Character, Integer>();
		for(char ch:characters.toCharArray()) {
			characterCount.put(ch, characterCount.getOrDefault(ch, 0)+1);
		}
		for(char ch:document.toCharArray()) {
			if(!characterCount.containsKey(ch) || characterCount.get(ch)==0)
				 return false;
			
			characterCount.put(ch, characterCount.get(ch)-1);
		}
		
		return true;
	}

	public static void main(String[] args) {
		String characters = "Bste!hetsi ogEAxpelrt x ";
		String document = "AlgoExpert is the Best!";
		System.out.println(generateDocument3(characters, document));

	}

}
