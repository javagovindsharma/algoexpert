package com.govind.alogexpert.hard;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutDuplication {
	public static String longestSubstringWithoutDuplication(String str) {
		Map<Character, Integer> lastSeen = new HashMap<Character, Integer>();
		int[] longest = { 0, 1 };
		int startIdx = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (lastSeen.containsKey(c)) {
				startIdx = Math.max(startIdx, lastSeen.get(c) + 1);
			}
			if (longest[1] - longest[0] < i + 1 - startIdx) {
				longest = new int[] { startIdx, i + 1 };
			}
			lastSeen.put(c, i);
		}
		String result = str.substring(longest[0], longest[1]);
		return result;
	}

	public static void main(String[] args) {
		assertEquals(LongestSubstringWithoutDuplication.longestSubstringWithoutDuplication("clementisacap"),
				"mentisac");

	}

}
