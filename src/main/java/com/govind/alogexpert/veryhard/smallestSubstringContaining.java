package com.govind.alogexpert.veryhard;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class smallestSubstringContaining {

	public static String smallestSubstringContaining(String bigString, String smallString) {
		Map<Character, Integer> targetCharCount = getCharCounts(smallString);
		List<Integer> substringBounds = getSubstringBounds(bigString, targetCharCount);
		return getStringFromBounds(bigString, substringBounds);
	}

	private static String getStringFromBounds(String string, List<Integer> bounds) {
		int start = bounds.get(0);
		int end = bounds.get(1);
		if (end == Integer.MAX_VALUE)
			return "";
		return string.substring(start, end + 1);
	}

	private static List<Integer> getSubstringBounds(String string, Map<Character, Integer> targetCharCount) {
		List<Integer> substringBounds = new ArrayList<Integer>(Arrays.asList(0, Integer.MAX_VALUE));
		Map<Character, Integer> substringCharCount = new HashMap<>();
		int numUniqueChar = targetCharCount.size();
		int numUniqueCharsDone = 0;
		int leftIdx = 0;
		int rightIdx = 0;

		while (rightIdx < string.length()) {
			char rightChar = string.charAt(rightIdx);
			if (!targetCharCount.containsKey(rightChar)) {
				rightIdx++;
				continue;
			}
			increaseCharCount(rightChar, substringCharCount);
			if (substringCharCount.get(rightChar).equals(targetCharCount.get(rightChar))) {
				numUniqueCharsDone++;
			}

			while (numUniqueCharsDone == numUniqueChar && leftIdx <= rightIdx) {
				substringBounds = getCloserBounds(leftIdx, rightIdx, substringBounds.get(0), substringBounds.get(1));
				char leftChar = string.charAt(leftIdx);
				if (!targetCharCount.containsKey(leftChar)) {
					leftIdx++;
					continue;
				}
				if (substringCharCount.get(leftChar).equals(targetCharCount.get(leftChar))) {
					numUniqueCharsDone--;
				}
				decreaseCharCount(leftChar, substringCharCount);
				leftIdx++;
			}
			rightIdx++;
		}
		return substringBounds;
	}

	private static void decreaseCharCount(char c, Map<Character, Integer> charCount) {
		charCount.put(c, charCount.get(c) - 1);
	}

	private static List<Integer> getCloserBounds(int idx1, int idx2, int idx3, int idx4) {
		return idx2 - idx1 < idx4 - idx3 ? new ArrayList<>(Arrays.asList(idx1, idx2))
				: new ArrayList<>(Arrays.asList(idx3, idx4));
	}

	private static Map<Character, Integer> getCharCounts(String string) {
		Map<Character, Integer> charCount = new HashMap<>();
		for (int i = 0; i < string.length(); i++) {
			increaseCharCount(string.charAt(i), charCount);
		}
		return charCount;
	}

	private static void increaseCharCount(char c, Map<Character, Integer> charCount) {
		if (!charCount.containsKey(c)) {
			charCount.put(c, 1);
		} else {
			charCount.put(c, charCount.get(c) + 1);
		}

	}

	public static void main(String[] args) {
		String bigString = "abcd$ef$axb$c$";
		String smallString = "$$abf";
		String expected = "f$axb$";
		assertTrue(smallestSubstringContaining.smallestSubstringContaining(bigString, smallString).equals(expected));
	}

}
