package com.govind.alogexpert.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

public class CountContainedPermutations {

	public static int countContainedPermutations(String bigString, String smallString) {
		Map<Character,Integer> smallStringCharCounts=getCharCounts(smallString);
		int numUniqueChars=smallStringCharCounts.size();
		Map<Character,Integer> runningCharCounts=new HashMap<Character,Integer>();
		int permutationCount=0;
		int numUniqueCharsDone=0;
		int leftIdx=0;
		int rightIdx=0;
		while(rightIdx<bigString.length()) {
			char rightChar=bigString.charAt(rightIdx);
			if(smallStringCharCounts.containsKey(rightIdx)) {
				increaseCharCount(rightChar,runningCharCounts);
				if(runningCharCounts.get(rightChar)==smallStringCharCounts.get(rightChar)) {
					numUniqueCharsDone++;
				}
			}
			
			if(numUniqueCharsDone==numUniqueChars) permutationCount++;
			rightIdx++;
			char leftChar=bigString.charAt(leftIdx);
			if(smallStringCharCounts.containsKey(leftChar)) {
				if(runningCharCounts.get(leftChar)==smallStringCharCounts.get(leftChar)) {
					numUniqueCharsDone--;
				}
				decreaseCharCount(leftChar,runningCharCounts);
			}
			leftIdx++;
		}
		
		return permutationCount;
	}

	private static Map<Character, Integer> getCharCounts(String string) {
		Map<Character,Integer> charCounts=new HashMap<Character,Integer>();
		for(char c:string.toCharArray()) {
			increaseCharCount(c,charCounts);
		}
		return charCounts;
	}

	private static void increaseCharCount(Character c, Map<Character, Integer> charCounts) {
		if(charCounts.containsKey(c)) {
			charCounts.put(c, charCounts.get(c)+1);
		}else {
			charCounts.put(c, 1);
		}
		
	}

	private static void decreaseCharCount(Character c, Map<Character, Integer> charCounts) {
		if(charCounts.containsKey(c)) {
			charCounts.put(c, charCounts.get(c)-1);
		}else {
			charCounts.put(c, -1);
		}
		
	}

	public static void main(String[] args) {
		String bigString = "cbabcacabca";
		String smallString = "abc";
		int expected = 6;
		int actual = CountContainedPermutations.countContainedPermutations(bigString, smallString);
		assertTrue(expected == actual);

	}

}
