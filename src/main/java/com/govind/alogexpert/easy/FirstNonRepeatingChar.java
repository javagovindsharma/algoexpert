package com.govind.alogexpert.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FirstNonRepeatingChar {

	public static int firstNonRepeatingCharacter1(String string) {
		Map<Character, Integer> maps = new HashMap<Character, Integer>();
		for (Character ch : string.toCharArray()) {
			if (maps.containsKey(ch)) {
				maps.put(ch, maps.get(ch) + 1);
			} else {
				maps.put(ch, 1);
			}
		}
		char ch = 0;
		for (Entry<Character, Integer> entry : maps.entrySet()) {
			if (entry.getValue() == 1) {
				ch = entry.getKey();
				break;
			}
		}
		int index = -1;
		char[] chVal = string.toCharArray();
		for (int i = 0; i < chVal.length; i++) {
			if (chVal[i] == ch) {
				index = i;
			}
		}

		return index;

	}

	/**
	 * second approach
	 * 
	 * @param args
	 */
	public static int firstNonRepeatingCharacter2(String string) {
		for (int i = 0; i < string.length(); i++) {
			boolean findDuplicate = false;
			for (int j = 0; j < string.length(); j++) {
				if (string.charAt(i) == string.charAt(j) && i != j) {
					findDuplicate = true;
				}
			}
			if (!findDuplicate)
				return i;
		}
		return -1;

	}

	public static int firstNonRepeatingCharacter(String string) {
		Map<Character, Integer> maps = new HashMap<Character, Integer>();
		for (Character ch : string.toCharArray()) {
			maps.put(ch, maps.getOrDefault(ch, 0) + 1);
		}
		for (int i = 0; i < string.length(); i++) {
			if(maps.get(string.charAt(i))==1) {
				return i;
			}
		}

		return -1;

	}

	public static void main(String[] args) {
		String str = "faadabcbbebdf";
		System.out.println(firstNonRepeatingCharacter(str));

	}

}
